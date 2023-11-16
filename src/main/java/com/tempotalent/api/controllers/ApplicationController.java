package com.tempotalent.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.Application;
import com.tempotalent.api.services.ApplicationService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class ApplicationController implements GraphQLMutationResolver, GraphQLQueryResolver {
  @Autowired
  private final ApplicationService applicationService;

  public ApplicationController(ApplicationService applicationService) {
    this.applicationService = applicationService;
  }

  @QueryMapping
  public List<Application> searchApplications() {
    
    return applicationService.fetch();
  }
  @QueryMapping
  public Application applicationById(@Argument UUID id) {
    return applicationService.fetchById(id);
  }

  @MutationMapping
  public Application addApplication( @Argument UUID jobofferid, @Argument UUID reviewid) {
    return applicationService.addApplication(jobofferid,reviewid);
  }

  @MutationMapping
  public Boolean deleteApplication(@Argument UUID id) {
    return applicationService.deleteApplication(id);
  }

}
