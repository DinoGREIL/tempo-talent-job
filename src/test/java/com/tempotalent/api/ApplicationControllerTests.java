package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.api.models.Application;

@SpringBootTest
@AutoConfigureGraphQlTester
class ApplicationControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String createQuery = "mutation addApplication( $jobofferid:ID, $reviewid:ID){ addApplication( jobofferid:$jobofferid, reviewid:$reviewid) { reviewid  } }";
  UUID idtotest=UUID.randomUUID();
  @Test
  void searchApplications() {
    var query = tester.document("query { searchApplications {  id } }");
    var results = query.execute().path("searchApplications").entityList(Application.class);

    assertTrue(results.get().size() > 0);
  }

  

  

  private Application addApplication() {
    var query = tester.document(createQuery);
    
    return query
        
        .variable("reviewid",idtotest)
        .variable("jobofferid", UUID.randomUUID()).execute().path("addApplication")
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

    assertEquals(idtotest, application.getReview().getId());
    assertTrue(deleted);
  }

 
}