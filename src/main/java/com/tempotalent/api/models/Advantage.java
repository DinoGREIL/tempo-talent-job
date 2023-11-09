package com.tempotalent.api.models;


import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "advantage")
public class Advantage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50)
  private String name;

  @OneToMany(mappedBy = "advantage")
  private Set<JobOffer> joboffers;

  

  public Advantage() {}

  public Advantage(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

 

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public Set<JobOffer> getJobOffers() {
    return joboffers;
  }
  
}
