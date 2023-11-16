package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import io.micrometer.common.lang.Nullable;
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
    return repository.findById(id).orElse(null);
  }

  

  


  public Review addReview(UUID id,Float rating, String message,Date providedAt) {
    var review = new Review(id, rating, message, providedAt);
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
