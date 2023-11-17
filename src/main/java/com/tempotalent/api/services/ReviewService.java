package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import io.micrometer.common.lang.Nullable;

import com.tempotalent.api.models.JobOffer;
import com.tempotalent.api.models.Review;

import com.tempotalent.api.repositories.ReviewRepository;

@Service
public class ReviewService {
  @Autowired
  private ReviewRepository repository;

  public ReviewService(ReviewRepository repository) {
    this.repository = repository;
  }

  public List<Review> fetch() {
    return repository.findAll();
  }

  @Nullable
  public Review fetchById(UUID id) {
    var review = new Review();
    review.setId(id);

    var matcher = ExampleMatcher.matching()
        .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

    var example = Example.of(review, matcher);

    return repository.findOne(example).orElse(null);
  }

  

  


  public Review addReview(UUID id,Float rating, String message,LocalDate providedat) {
    var review = new Review(id, rating, message, providedat);
    System.out.println("\001b[31m " + review.getId() + "\001b[0m");
    return repository.save(review);
  }

  public Boolean deleteReview(UUID id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
