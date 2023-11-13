package com.tempotalent.api.models;

import java.util.UUID;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "advantage")
public class Advantage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @Column(length = 50)
  private String name;

  @OneToMany(mappedBy = "advantage")
  private Set<JobOfferAdvantage> jobOfferAdvantages;

  

  public Advantage() {}

  public Advantage(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

 

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public Set<JobOfferAdvantage> getJobOfferAdvantages() {
    return jobOfferAdvantages;
  }
  
}
