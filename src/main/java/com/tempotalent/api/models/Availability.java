package com.tempotalent.api.models;


import java.util.UUID;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "availability")
public class Availability {
  @Id
  
  private UUID id;

  
  @Column(length = 50)
  private LocalDate startdate;
  @Column(length = 50)
  private LocalDate enddate;
  
  

  @ManyToOne
  @JoinColumn(name = "job")
  private Job job;

  public Availability() {}

  public Availability(UUID id, LocalDate startdate, LocalDate enddate,UUID jobid) {
    this.id = id;
    
    this.startdate=startdate;
    this.enddate=enddate;
    
    this.job = new Job();
    this.job.setId(jobid);
  }

  

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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
  
  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }
}
