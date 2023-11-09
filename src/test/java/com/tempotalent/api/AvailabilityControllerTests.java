package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.GraphQlTester.Path;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.api.models.Availability;

@SpringBootTest
@AutoConfigureGraphQlTester
class AvailabilityControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String createQuery = "mutation addAvailability($id:ID, $startdate:Date,$enddate:Date,  $jobid:ID){ addAvailability(id:$id, startdate:$startdate,enddate:$enddate,  jobid:$jobid) { startdate  } }";

  @Test
  void searchAvailabilities() {
    var query = tester.document("query { searchAvailabilities { startdate enddate } }");
    var results = query.execute().path("searchAvailabilities").entityList(Availability.class);

    assertTrue(results.get().size() > 0);
  }

  

  

  private Availability addAvailability() {
    var query = tester.document(createQuery);
    return ((Path) query
        .variable("id", 5)
        
        .variable("startdate", new Date())
        .variable("enddate",new Date())
        
        .variable("jobid",12))
        .entity(Availability.class).get();
  }

  

  private Boolean deleteAvailability(UUID id) {
    var query = tester.document("mutation deleteAvailability($id: ID!) {deleteAvailability(id: $id)}");
    return query.variable("id", id).execute().path("deleteAvailability").entity(Boolean.class).get();
  }

  @Test
  @Transactional
  void testAddDeleteAvailability() {
    var availability = addAvailability();
    var deleted = deleteAvailability(availability.getId());

    assertEquals(5, availability.getId());
    assertTrue(deleted);
  }

 
}