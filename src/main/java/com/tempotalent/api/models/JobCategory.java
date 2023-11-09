package com.tempotalent.api.models;



import jakarta.persistence.*;

@Entity
@Table(name = "job_category")
public class JobCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50)
  private String name;

  

  

  public JobCategory() {}

  public JobCategory(Integer id, String name) {
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

  
}
