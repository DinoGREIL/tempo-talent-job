package com.tempotalent.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.Feature;
import com.tempotalent.api.repositories.FeatureRepository;

import io.micrometer.common.lang.Nullable;

@Controller
public class FeatureController {
  @Autowired
  private final FeatureRepository repository;

  public FeatureController(FeatureRepository repository) {
    this.repository = repository;
  }

  @QueryMapping
  public List<Feature> features() {
    return repository.findAll();
  }

  @Nullable
  @QueryMapping
  public Feature featureById(@Argument Integer id) {
    return repository.findById(id).orElse(null);
  }

  @MutationMapping
  public Feature addFeature(@Argument String name) {
    return repository.save(new Feature(null, name));
  }

  @MutationMapping
  public Boolean deleteFeature(@Argument Integer id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
