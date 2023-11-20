package com.tempotalent.api.models;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "job_offeradvantage")
@IdClass(JobOfferAdvantageKey.class)
public class JobOfferAdvantage {
  @Id
  @Column(name = "idadvantage", nullable = false)
  private UUID idadvantage;

  @Id
  @Column(name = "idjob_offer", nullable = false)
  private UUID idjob_offer;



  @ManyToOne
  @JoinColumn(name = "idadvantage", referencedColumnName = "id", insertable = false)
  private Advantage advantage;

  @ManyToOne
  @JoinColumn(name = "idjob_offer", referencedColumnName = "id", insertable = false)
  private JobOffer jobOffer;


  public JobOfferAdvantage() {
  }
  
  public JobOfferAdvantage(UUID idadvantage, UUID idjob_offer) {
    this.idadvantage = idadvantage;
    this.idjob_offer = idjob_offer;
    
  }

  

  public JobOffer getJobOffer() {
    return jobOffer;
  }

  public void setJobOffer(JobOffer jobOffer) {
    this.jobOffer = jobOffer;
  }

  public Advantage getAdvantage() {
    return advantage;
  }

  public void setAdvantage(Advantage advantage) {
    this.advantage = advantage;
  }
}
