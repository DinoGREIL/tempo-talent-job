package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.models.Application;

class ApplicationControllerTests extends AbstractTest {
  private final String createQuery = "mutation addApplication( $jobofferid:ID, $reviewid:ID){ addApplication( jobofferid:$jobofferid, reviewid:$reviewid) {id review {id}  } }";

  @Test
  void searchApplications() {
    var query = tester.document("query { searchApplications {  id } }");
    var results = query.execute().path("searchApplications").entityList(Application.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  @Transactional
  void testApplicationById() {
    var query = tester.document(
        "query { applicationById(id: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\") { id  } }");
    var result = query.execute().path("applicationById").entity(Application.class).get();

    assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), result.getId());
  }

  private Application addApplication() {
    var query = tester.document(createQuery);

    return query

        .variable("reviewid", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
        .variable("jobofferid", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11").execute().path("addApplication")
        .entity(Application.class).get();
  }

  private Boolean deleteApplication(UUID id) {
    var query = tester.document("mutation deleteApplication($id: ID!) {deleteApplication(id: $id)}");
    return query.variable("id", id).execute().path("deleteApplication").entity(Boolean.class).get();
  }

  @Test
  @Transactional
  void testAddDeleteApplication() {
    var application = addApplication();
    var deleted = deleteApplication(application.getId());

    assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), application.getReview().getId());
    assertTrue(deleted);
  }

}