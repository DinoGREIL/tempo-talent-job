package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import com.tempotalent.api.models.JobCategory;
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
    var jobOffer = new JobOffer();
    jobOffer.setId(id);

    var matcher = ExampleMatcher.matching()
        .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

    var example = Example.of(jobOffer, matcher);

    return repository.findOne(example).orElse(null);
  }

  

  


  public JobOffer addJobOffer(String description,LocalDate startdate, LocalDate enddate,Integer salary,UUID jobid) {
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
