package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.api.models.JobOffer;

@SpringBootTest
@AutoConfigureGraphQlTester
class JobOfferControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String createQuery = "mutation addJobOffer($description:String,$startdate:Date,$enddate:Date,$salary:Int,$jobid:ID){ addJobOffer(description:$description,startdate:$startdate,enddate:$enddate,salary:$salary,jobid:$jobid) { description  } }";

  @Test
  void searchJobOffers() {
    var query = tester.document("query { searchJobOffers { description  } }");
    var results = query.execute().path("searchJobOffers").entityList(JobOffer.class);

    assertTrue(results.get().size() > 0);
  }

  

  

  private JobOffer addJobOffer() {
    var query = tester.document(createQuery);
    return query
        .variable("description", "Test JobOffer input")
        .variable("startdate", new Date())
        .variable("enddate",new Date())
        .variable("salary",1200)
        .variable("jobid",UUID.randomUUID())
        
        .execute().path("addJobOffer")
        .entity(JobOffer.class).get();
  }

  

  private Boolean deleteJobOffer(UUID uuid) {
    var query = tester.document("mutation deleteJobOffer($id: ID!) {deleteJobOffer(id: $id)}");
    return query.variable("id", uuid).execute().path("deleteJobOffer").entity(Boolean.class).get();
  }

  @Test
  @Transactional
  void testAddDeleteJobOffer() {
    var joboffer = addJobOffer();
    var deleted = deleteJobOffer(joboffer.getId());

    assertEquals("Test JobOffer input", joboffer.getDescription());
    assertTrue(deleted);
  }

 
}