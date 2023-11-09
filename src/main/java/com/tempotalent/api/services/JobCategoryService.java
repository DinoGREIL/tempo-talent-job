package com.tempotalent.api.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import io.micrometer.common.lang.Nullable;
import com.tempotalent.api.models.JobCategory;

import com.tempotalent.api.repositories.JobCategoryRepository;

@Service
public class JobCategoryService {
  @Autowired
  private JobCategoryRepository repository;

  public JobCategoryService(JobCategoryRepository repository) {
    this.repository = repository;
  }

  public List<JobCategory> fetch() {
    return repository.findAll();
  }

  @Nullable
  public JobCategory fetchById(Integer id) {
    return repository.findById(id).orElse(null);
  }

  

  


  public JobCategory addJobCategory(Integer id, String name) {
    var job_category = new JobCategory(id, name);
    System.out.println("\001b[31m " + job_category.getId() + "\001b[0m");
    return repository.save(job_category);
  }

  public Boolean deleteJobCategory(Integer id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
