package com.tempotalent.api.models;

import java.time.LocalDate;

import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "job_offer")
public class JobOffer {
  @Id
  
  private UUID id;

  @Column(length = 50)
  private String description;
  @Column(name="startdate",length = 50)
  private LocalDate startdate;
  @Column(name="enddate",length = 50)
  private LocalDate enddate;
  @Column(length = 50)
  private Integer salary;
  @ManyToOne
  @JoinColumn(name = "job")
  private Job job;

  

  @OneToMany(mappedBy = "jobOffer")
  private Set<JobOfferAdvantage> jobOfferAdvantages;
  public JobOffer() {}

  

  public JobOffer(String description,LocalDate startdate, LocalDate enddate,Integer salary,UUID jobid) {
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

  public LocalDate getStartDate() {
    return startdate;
  }

  public void setStartDate(LocalDate startdate) {
    this.startdate = startdate;
  }
  public LocalDate getEndDate() {
    return enddate;
  }

  public void setEndDate(LocalDate enddate) {
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
