package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.Date;
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
    return repository.findById(id).orElse(null);
  }

  

  


  public Availability addAvailability(UUID id, Date startdate, Date enddate,UUID jobid) {
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
