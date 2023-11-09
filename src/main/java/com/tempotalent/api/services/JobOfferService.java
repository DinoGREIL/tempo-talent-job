package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.Date;
import com.tempotalent.api.models.JobOffer;

import com.tempotalent.api.repositories.JobOfferRepository;

@Service
public class JobOfferService {
  @Autowired
  private JobOfferRepository repository;

  public JobOfferService(JobOfferRepository repository) {
    this.repository = repository;
  }

  public List<JobOffer> fetch() {
    return repository.findAll();
  }

  @Nullable
  public JobOffer fetchById(UUID id) {
    return repository.findById(id).orElse(null);
  }

  

  


  public JobOffer addJobOffer(String description,Date startdate, Date enddate,Integer salary,UUID jobid) {
    var job_offer = new JobOffer(description,startdate,enddate,salary,jobid);
    System.out.println("\001b[31m " + job_offer.getId() + "\001b[0m");
    return repository.save(job_offer);
  }

  public Boolean deleteJobOffer(UUID id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
