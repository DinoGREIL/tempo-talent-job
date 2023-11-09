package com.tempotalent.api.models;


import java.util.UUID;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @Column(length = 50)
  private String message;
  
  @Column(length = 50)
  private Float rating;
  
  @Column(length = 50)
  private Date providedAt;

  

  public Review() {}

  public Review(UUID id,Float rating, String message,Date providedAt) {
    this.id = id;
    this.message = message;
    this.rating=rating;
    this.providedAt=providedAt;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  public Float getRating() {
    return rating;
  }

  public void setRating(Float rating) {
    this.rating = rating;
  }
  public Date getProvidedAt() {
    return providedAt;
  }

  public void setProvidedAt(Date providedAt) {
    this.providedAt = providedAt;
  }
}
