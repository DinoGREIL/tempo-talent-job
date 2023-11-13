package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import io.micrometer.common.lang.Nullable;
import org.springframework.stereotype.Service;

import com.tempotalent.api.models.Advantage;

import com.tempotalent.api.repositories.AdvantageRepository;

@Service
public class AdvantageService {
  @Autowired
  private AdvantageRepository repository;

  public AdvantageService(AdvantageRepository repository) {
    this.repository = repository;
  }

  public List<Advantage> fetch() {
    return repository.findAll();
  }

  @Nullable
  public Advantage fetchById(UUID id) {
    return repository.findById(id).orElse(null);
  }

  

  


  public Advantage addAdvantage(UUID id, String name) {
    var advantage = new Advantage(id,name);
    System.out.println("\001b[31m " + advantage.getId() + "\001b[0m");
    return repository.save(advantage);
  }

  public Boolean deleteAdvantage(UUID id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
