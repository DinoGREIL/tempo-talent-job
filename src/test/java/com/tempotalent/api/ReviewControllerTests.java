package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.api.models.Job;
import com.tempotalent.api.models.Review;

@SpringBootTest
@AutoConfigureGraphQlTester
class ReviewControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String createQuery = "mutation addReview($id:ID,$rating:Float, $message:String, $providedat:Date){ addReview(id:$id,rating:$rating, message:$message, providedat:$providedat) {id message  rating } }";

  @Test
  void searchReviews() {
    var query = tester.document("query { searchReviews { rating message } }");
    var results = query.execute().path("searchReviews").entityList(Review.class);

    assertTrue(results.get().size() > 0);
  }
  @Test
  @Transactional
  void testReviewById() {
    var query = tester.document(
        "query { reviewById(id: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\") { id  } }");
    var result = query.execute().path("reviewById").entity(Review.class).get();

    assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), result.getId());
  }

  

  

  private Review addReview() {
    var query = tester.document(createQuery);
    return query
    .variable("id",UUID.randomUUID())
        .variable("rating", 5.0)
        .variable("message","Test review input")
        .variable("providedat", LocalDate.now()).execute().path("addReview")
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