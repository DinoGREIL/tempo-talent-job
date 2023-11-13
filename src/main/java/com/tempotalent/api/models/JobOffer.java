package com.tempotalent.api.models;

import java.util.Date;
import java.util.Set;
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
  @Column(name="start",length = 50)
  private Date startdate;
  @Column(name="end",length = 50)
  private Date enddate;
  @Column(length = 50)
  private Integer salary;
  @ManyToOne
  @JoinColumn(name = "job")
  private Job job;

  

  @OneToMany(mappedBy = "jobOffer")
  private Set<JobOfferAdvantage> jobOfferAdvantages;
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
