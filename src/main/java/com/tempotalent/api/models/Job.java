package com.tempotalent.api.models;


import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "job")
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50)
  private String title;

  @ManyToOne
  @JoinColumn(name = "category")
  private JobCategory category;

  

  public Job() {}

  

  public Job(Integer id,String title, Integer categoryid) {
    this.title = title;
    this.id=id;
    this.category = new JobCategory();
    this.category.setId(categoryid);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public JobCategory getCategory() {
    return category;
  }

  public void setCategory(JobCategory category) {
    this.category = category;
  }
}
