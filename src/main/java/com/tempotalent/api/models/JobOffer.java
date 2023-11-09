package com.tempotalent.api.models;

import java.util.Date;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "job_offer")
public class JobOffer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @Column(length = 50)
  private String description;
  @Column(length = 50)
  private Date startdate;
  @Column(length = 50)
  private Date enddate;
  @Column(length = 50)
  private Integer salary;
  

  @ManyToOne
  @JoinColumn(name = "job_id")
  private Job job;

  public JobOffer() {}

  

  public JobOffer(String description,Date startdate, Date enddate,Integer salary,UUID jobid) {
    this.id=UUID.randomUUID();
    this.description = description;
    this.startdate=startdate;
    this.enddate=enddate;
    this.salary=salary;
    this.job = new Job();
    this.job.setId(jobid);
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStartDate() {
    return startdate;
  }

  public void setStartDate(Date startdate) {
    this.startdate = startdate;
  }
  public Date getEndDate() {
    return enddate;
  }

  public void setEndDate(Date enddate) {
    this.enddate = enddate;
  }
  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }
  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }
}
