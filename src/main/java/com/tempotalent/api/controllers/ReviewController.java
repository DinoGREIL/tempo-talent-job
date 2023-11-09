package com.tempotalent.api.controllers;

import java.util.List;
import java.util.UUID;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.Review;
import com.tempotalent.api.services.ReviewService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class ReviewController implements GraphQLMutationResolver, GraphQLQueryResolver {
  @Autowired
  private final ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @QueryMapping
  public List<Review> searchReviews() {
    
    return reviewService.fetch();
  }

  @MutationMapping
  public Review addReview(@Argument Float rating,@Argument String message,@Argument Date providedAt) {
    return reviewService.addReview(rating,message,providedAt);
  }

  @MutationMapping
  public Boolean deleteReview(@Argument UUID id) {
    return reviewService.deleteReview(id);
  }

}
