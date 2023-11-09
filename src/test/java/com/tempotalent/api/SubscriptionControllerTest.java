package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.tempotalent.api.models.Subscription;

@SpringBootTest
@AutoConfigureGraphQlTester
class SubscriptionControllerTest {
  @Autowired
  private GraphQlTester tester;

  @Test
  void testSubscriptionAddingDeleting() {
    var query = tester.document("mutation { deleteSubscriptionFromRecruiter(recruiterId: \"a1b2c3d4-1234-5678-9012-abcdef123456\", tierId: 1) }");
    var deleted = query.execute().path("deleteSubscriptionFromRecruiter").entity(Boolean.class).get();

    query = tester.document(
      "mutation { addSubscriptionToRecruiter(recruiterId: \"a1b2c3d4-1234-5678-9012-abcdef123456\", tierId: 1, startDate: \"2023-10-31\", enDate: \"2023-11-07\") { tier { id } recruiter { id } startDate endDate } }");
    var results = query.execute().path("addSubscriptionToRecruiter").entity(Subscription.class).get();

    assertTrue(deleted);
    assertEquals(1, results.getTier().getId());
  }
  
}
