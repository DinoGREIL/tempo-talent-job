package com.tempotalent.api.controllers;

import java.util.List;
import java.util.UUID;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.Availability;
import com.tempotalent.api.services.AvailabilityService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class AvailabilityController implements GraphQLMutationResolver, GraphQLQueryResolver {
  @Autowired
  private final AvailabilityService availabilityService;

  public AvailabilityController(AvailabilityService availabilityService) {
    this.availabilityService = availabilityService;
  }

  @QueryMapping
  public List<Availability> searchAvailabilities() {
    
    return availabilityService.fetch();
  }

  @MutationMapping
  public Availability addAvailability(@Argument Integer id, @Argument Date startdate, @Argument Date enddate, @Argument Integer jobid) {
    return availabilityService.addAvailability(id,startdate,enddate,jobid);
  }

  @MutationMapping
  public Boolean deleteAvailability(@Argument Integer id) {
    return availabilityService.deleteAvailability(id);
  }

}
