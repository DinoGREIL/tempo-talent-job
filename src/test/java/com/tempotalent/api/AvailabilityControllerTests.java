package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.api.models.Availability;

@SpringBootTest
@AutoConfigureGraphQlTester
class AvailabilityControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String createQuery = "mutation addAvailability($id:ID, $startdate:Date,$enddate:Date,  $jobid:ID){ addAvailability(id:$id, startdate:$startdate,enddate:$enddate,  jobid:$jobid) { id startdate  } }";

  @Test
  void searchAvailabilities() {
    var query = tester.document("query { searchAvailabilities { id  } }");
    var results = query.execute().path("searchAvailabilities").entityList(Availability.class);

    assertTrue(results.get().size() > 0);
  }

  

  

  private Availability addAvailability() {
    var query = tester.document(createQuery);
    return  query
        .variable("id", "2240ba08-19e1-4038-beb0-40a4c5208349")
        
        .variable("startdate", LocalDate.now())
        .variable("enddate",LocalDate.now())
        
        .variable("jobid","a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
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