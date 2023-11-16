package com.tempotalent.api.models;


import java.util.UUID;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
  @Id
  
  private UUID id;

  @Column(length = 50)
  private String message;
  
  @Column(length = 50)
  private Float rating;
  
  @Column(name="providedat",length = 50)
  private LocalDate providedat;

  

  public Review() {}

  public Review(UUID id,Float rating, String message,LocalDate providedat) {
    this.id = id;
    this.message = message;
    this.rating=rating;
    this.providedat=providedat;
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
  public LocalDate getProvidedAt() {
    return providedat;
  }

  public void setProvidedAt(LocalDate providedat) {
    this.providedat = providedat;
  }
}
