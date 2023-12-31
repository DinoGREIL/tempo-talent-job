package com.tempotalent.api.controllers;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.Advantage;
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
  @QueryMapping
  public Review reviewById(@Argument UUID id) {
    return reviewService.fetchById(id);
  }
  @MutationMapping
  public Review addReview(@Argument UUID id,@Argument Float rating,@Argument String message,@Argument LocalDate providedat) {
    return reviewService.addReview(id,rating,message,providedat);
  }

  @MutationMapping
  public Boolean deleteReview(@Argument UUID id) {
    return reviewService.deleteReview(id);
  }

}
