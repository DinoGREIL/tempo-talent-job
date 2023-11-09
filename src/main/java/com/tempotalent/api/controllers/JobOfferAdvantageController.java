package com.tempotalent.api.controllers;

import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.JobOfferAdvantage;
import com.tempotalent.api.services.JobOfferAdvantageService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class JobOfferAdvantageController implements GraphQLMutationResolver, GraphQLQueryResolver {
  private final JobOfferAdvantageService jobOfferAdvantageService;

  public JobOfferAdvantageController(JobOfferAdvantageService jobOfferAdvantageService) {
    this.jobOfferAdvantageService = jobOfferAdvantageService;
  }

  @MutationMapping
  public JobOfferAdvantage addAdvantageToJobOffer(@Argument Integer advantageId, @Argument UUID jobOfferId) {
    return jobOfferAdvantageService.addAdvantageToJobOffer(advantageId, jobOfferId);
  }

  @MutationMapping
  public Boolean removeAdvantageFromJobOffer(@Argument Integer advantageId, @Argument UUID jobOfferId) {
    return jobOfferAdvantageService.removeAdvantageFromJobOffer(advantageId, jobOfferId);
  }
}

