package com.tempotalent.api.models;


import java.util.UUID;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "availability")
public class Availability {
  @Id
  
  private UUID id;

  
  @Column(length = 50)
  private Date startdate;
  @Column(length = 50)
  private Date enddate;
  
  

  @ManyToOne
  @JoinColumn(name = "job")
  private Job job;

  public Availability() {}

  public Availability(UUID id, Date startdate, Date enddate,UUID jobid) {
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
  
  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }
}
