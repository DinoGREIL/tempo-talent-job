package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import io.micrometer.common.lang.Nullable;

import com.tempotalent.api.models.Advantage;
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
   var application = new Application();
    application.setId(id);

    var matcher = ExampleMatcher.matching()
        .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

    var example = Example.of(application, matcher);

    return repository.findOne(example).orElse(null);
  }

  

  


  public Application addApplication( UUID jobofferid, UUID reviewid) {
    var application = new Application( jobofferid, reviewid);
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
