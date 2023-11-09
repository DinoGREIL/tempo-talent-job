package com.tempotalent.api.controllers;

import java.util.List;
import java.util.UUID;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.JobOffer;
import com.tempotalent.api.services.JobOfferService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class JobOfferController implements GraphQLMutationResolver, GraphQLQueryResolver {
  @Autowired
  private final JobOfferService jobOfferService;

  public JobOfferController(JobOfferService jobOfferService) {
    this.jobOfferService = jobOfferService;
  }

  @QueryMapping
  public List<JobOffer> searchJobOffers() {
    
    return jobOfferService.fetch();
  }

  @MutationMapping
  public JobOffer addJobOffer(@Argument String description,@Argument Date startdate,@Argument Date enddate,@Argument Integer salary,@Argument UUID jobid) {
    return jobOfferService.addJobOffer(description,startdate,enddate,salary,jobid);
  }

  @MutationMapping
  public Boolean deleteJobOffer(@Argument UUID id) {
    return jobOfferService.deleteJobOffer(id);
  }

}
