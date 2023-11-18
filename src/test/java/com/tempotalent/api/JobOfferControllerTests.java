package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.models.JobOffer;

class JobOfferControllerTests extends AbstractTest {

  private final String createQuery = "mutation addJobOffer($description:String,$startdate:Date,$enddate:Date,$salary:Int,$jobid:ID){ addJobOffer(description:$description,startdate:$startdate,enddate:$enddate,salary:$salary,jobid:$jobid) { id description  } }";

  @Test
  void searchJobOffers() {
    var query = tester.document("query { searchJobOffers { description  } }");
    var results = query.execute().path("searchJobOffers").entityList(JobOffer.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  @Transactional
  void testJobOfferById() {
    var query = tester.document(
        "query { jobOfferById(id: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\") { id  } }");
    var result = query.execute().path("jobOfferById").entity(JobOffer.class).get();

    assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), result.getId());
  }

  private JobOffer addJobOffer() {
    var query = tester.document(createQuery);
    return query
        .variable("description", "Test JobOffer input")
        .variable("startdate", LocalDate.now())
        .variable("enddate", LocalDate.now())
        .variable("salary", 1200)
        .variable("jobid", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")

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