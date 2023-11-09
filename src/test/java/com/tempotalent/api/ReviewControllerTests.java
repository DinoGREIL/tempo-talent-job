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

import com.tempotalent.api.models.Review;

@SpringBootTest
@AutoConfigureGraphQlTester
class ReviewControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String createQuery = "mutation addReview($rating:Float, $message:String, $providedAt:Date){ addReview(rating:$rating, message:$message, providedAt:$providedAt) { message  } }";

  @Test
  void searchReviews() {
    var query = tester.document("query { companies { siret name } }");
    var results = query.execute().path("searchReviews").entityList(Review.class);

    assertTrue(results.get().size() > 0);
  }

  

  

  private Review addReview() {
    var query = tester.document(createQuery);
    return query
        .variable("rating", 5.0)
        .variable("message","Test review input")
        .variable("providedAt", new Date()).execute().path("addReview")
        .entity(Review.class).get();
  }

  

  private Boolean deleteReview(UUID uuid) {
    var query = tester.document("mutation deleteReview($id: ID!) {deleteReview(id: $id)}");
    return query.variable("id", uuid).execute().path("deleteReview").entity(Boolean.class).get();
  }

  @Test
  @Transactional
  void testAddDeleteReview() {
    var review = addReview();
    var deleted = deleteReview(review.getId());
    float i=5;
    assertEquals(i, review.getRating());
    assertTrue(deleted);
  }

 
}