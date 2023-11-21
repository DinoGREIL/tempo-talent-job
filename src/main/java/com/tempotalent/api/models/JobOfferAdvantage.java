package com.tempotalent.api.models;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "job_offeradvantage")

public class JobOfferAdvantage {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;
  @Column(name = "idadvantage", nullable = false,updatable=false)
  private UUID idadvantage;

  
  @Column(name = "idjob_offer", nullable = false,updatable=false)
  private UUID idjob_offer;



  @ManyToOne
  @JoinColumn(name = "idadvantage", referencedColumnName = "id", insertable = false,updatable=false)
  private Advantage advantage;

  @ManyToOne
  @JoinColumn(name = "idjob_offer", referencedColumnName = "id", insertable = false,updatable=false)
  private JobOffer jobOffer;


  public JobOfferAdvantage() {
  }
  
  public JobOfferAdvantage(UUID id,UUID idadvantage, UUID idjob_offer) {
    this.idadvantage = idadvantage;
    this.idjob_offer = idjob_offer;
    this.id=id;
    
  }

  
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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
