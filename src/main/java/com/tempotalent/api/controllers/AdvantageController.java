package com.tempotalent.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.Advantage;
import com.tempotalent.api.services.AdvantageService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class AdvantageController implements GraphQLMutationResolver, GraphQLQueryResolver {
  @Autowired
  private final AdvantageService advantageService;

  public AdvantageController(AdvantageService advantageService) {
    this.advantageService = advantageService;
  }

  @QueryMapping
  public List<Advantage> searchAdvantages() {
    
    return advantageService.fetch();
  }
  @QueryMapping
  public Advantage advantageById(@Argument UUID id) {
    return advantageService.fetchById(id);
  }

  @MutationMapping
  public Advantage addAdvantage(@Argument UUID id,@Argument String name) {
    return advantageService.addAdvantage(id,name);
  }

  @MutationMapping
  public Boolean deleteAdvantage(@Argument UUID id) {
    return advantageService.deleteAdvantage(id);
  }

}
