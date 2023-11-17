package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import com.tempotalent.api.models.Application;
import com.tempotalent.api.models.Availability;

import com.tempotalent.api.repositories.AvailabilityRepository;

@Service
public class AvailabilityService {
  @Autowired
  private AvailabilityRepository repository;

  public AvailabilityService(AvailabilityRepository repository) {
    this.repository = repository;
  }

  public List<Availability> fetch() {
    return repository.findAll();
  }

  @Nullable
  public Availability fetchById(UUID id) {
    var availability = new Availability();
    availability.setId(id);

    var matcher = ExampleMatcher.matching()
        .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

    var example = Example.of(availability, matcher);

    return repository.findOne(example).orElse(null);
  }

  

  


  public Availability addAvailability(UUID id, LocalDate startdate, LocalDate enddate,UUID jobid) {
    var availability = new Availability(id,startdate,enddate,jobid);
    System.out.println("\001b[31m " + availability.getId() + "\001b[0m");
    return repository.save(availability);
  }

  public Boolean deleteAvailability(UUID id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
