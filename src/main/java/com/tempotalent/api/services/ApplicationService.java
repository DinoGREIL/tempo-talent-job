package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import io.micrometer.common.lang.Nullable;
import com.tempotalent.api.models.Application;

import com.tempotalent.api.repositories.ApplicationRepository;

@Service
public class ApplicationService {
  @Autowired
  private ApplicationRepository repository;

  public ApplicationService(ApplicationRepository repository) {
    this.repository = repository;
  }

  public List<Application> fetch() {
    return repository.findAll();
  }

  @Nullable
  public Application fetchById(UUID id) {
    return repository.findById(id).orElse(null);
  }

  

  


  public Application addApplication(UUID candidateid, UUID jobofferid, UUID reviewid) {
    var application = new Application( candidateid, jobofferid, reviewid);
    System.out.println("\001b[31m " + application.getId() + "\001b[0m");
    return repository.save(application);
  }

  public Boolean deleteApplication(UUID id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
