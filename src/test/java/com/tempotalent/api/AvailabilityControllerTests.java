package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.models.Availability;

class AvailabilityControllerTests extends AbstractTest {
  private final String createQuery = "mutation addAvailability($id:ID, $startdate:Date,$enddate:Date,  $jobid:ID){ addAvailability(id:$id, startdate:$startdate,enddate:$enddate,  jobid:$jobid) { id startdate  } }";

  @Test
  void searchAvailabilities() {
    var query = tester.document("query { searchAvailabilities { id  } }");
    var results = query.execute().path("searchAvailabilities").entityList(Availability.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  @Transactional
  void testAvailabilityById() {
    var query = tester.document(
        "query { availabilityById(id: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\") { id  } }");
    var result = query.execute().path("availabilityById").entity(Availability.class).get();

    assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), result.getId());
  }

  private Availability addAvailability() {
    var query = tester.document(createQuery);
    return query
        .variable("id", "2240ba08-19e1-4038-beb0-40a4c5208349")

        .variable("startdate", LocalDate.now())
        .variable("enddate", LocalDate.now())

        .variable("jobid", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
        .execute().path("addAvailability")
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

    assertEquals(UUID.fromString("2240ba08-19e1-4038-beb0-40a4c5208349"), availability.getId());
    assertTrue(deleted);
  }

}