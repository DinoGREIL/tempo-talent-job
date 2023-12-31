package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.tempotalent.api.models.Job;
import com.tempotalent.api.models.JobOffer;
import com.tempotalent.api.repositories.JobRepository;

@Service
public class JobService {
  @Autowired
  private JobRepository repository;

  public JobService(JobRepository repository) {
    this.repository = repository;
  }

  public List<Job> fetch() {
    return repository.findAll();
  }

  @Nullable
  public Job fetchById(UUID id) {
    var job = new Job();
    job.setId(id);

    var matcher = ExampleMatcher.matching()
        .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

    var example = Example.of(job, matcher);

    return repository.findOne(example).orElse(null);
  }

  

  


  public Job addJob(UUID id,String title, UUID categoryid) {
    var job = new Job(id,title, categoryid);
    System.out.println("\001b[31m " + job.getId() + "\001b[0m");
    return repository.save(job);
  }

  public Boolean deleteJob(UUID id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
