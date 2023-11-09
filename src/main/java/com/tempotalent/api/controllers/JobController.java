package com.tempotalent.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.Job;
import com.tempotalent.api.services.JobService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class JobController implements GraphQLMutationResolver, GraphQLQueryResolver {
  @Autowired
  private final JobService jobService;

  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @QueryMapping
  public List<Job> searchJobs() {
    
    return jobService.fetch();
  }

  @MutationMapping
  public Job addJob(@Argument String title, @Argument Integer categoryid) {
    return jobService.addJob(title,categoryid);
  }

  @MutationMapping
  public Boolean deleteJob(@Argument UUID id) {
    return jobService.deleteJob(id);
  }

}
