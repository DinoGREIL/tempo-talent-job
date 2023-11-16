package com.tempotalent.api.models;



import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "job_category")
public class JobCategory {
  @Id
  
  private UUID id;

  @Column(length = 50)
  private String name;

  

  

  public JobCategory() {}

  public JobCategory(UUID id, String name) {
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

  
}
