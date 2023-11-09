package com.tempotalent.api.models;


import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "job")
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @Column(length = 50)
  private String title;

  @ManyToOne
  @JoinColumn(name = "job_category_id")
  private JobCategory category;

  

  public Job() {}

  

  public Job(String title, Integer categoryid) {
    this.title = title;
    this.id=UUID.randomUUID();
    this.category = new JobCategory();
    this.category.setId(categoryid);
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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
