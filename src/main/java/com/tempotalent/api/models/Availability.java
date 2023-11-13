package com.tempotalent.api.models;


import java.util.UUID;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "availability")
public class Availability {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  
  @Column(length = 50)
  private Date startdate;
  @Column(length = 50)
  private Date enddate;
  
  

  @ManyToOne
  @JoinColumn(name = "job_id")
  private Job job;

  public Availability() {}

  public Availability(Integer id, Date startdate, Date enddate,Integer jobid) {
    this.id = id;
    
    this.startdate=startdate;
    this.enddate=enddate;
    
    this.job = new Job();
    this.job.setId(jobid);
  }

  

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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
