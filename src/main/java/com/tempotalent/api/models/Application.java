package com.tempotalent.api.models;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    //private Candidate candidate;
    private JobOffer job_offer;
    private Review review;

    public Application() {
    }

    public Application(UUID candidateid, UUID jobofferid, UUID reviewid) {
        this.id = UUID.randomUUID();
        this.job_offer = new JobOffer();
        //this.candidate = new Candidate();
        this.review = new Review();
        
        this.job_offer.setId(candidateid);
        //this.candidate.setId(jobofferid);
        this.review.setId(reviewid);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

   

    public JobOffer getJobOffer() {
        return job_offer;
    }

    public void setJobOffer(JobOffer job_offer) {
        this.job_offer = job_offer;
    }
//     public Candidate getCandidate() {
//       return candidate;
//   }

//   public void setCandidate(Candidate candidate) {
//       this.candidate = candidate;
//   }
  public Review getReview() {
    return review;
}

public void setReview(Review review) {
    this.review = review;
}


}