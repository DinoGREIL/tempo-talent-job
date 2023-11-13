package com.tempotalent.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.JobCategory;
import com.tempotalent.api.services.JobCategoryService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class JobCategoryController implements GraphQLMutationResolver, GraphQLQueryResolver {
  @Autowired
  private final JobCategoryService jobCategoryService;

  public JobCategoryController(JobCategoryService jobCategoryService) {
    this.jobCategoryService = jobCategoryService;
  }

  @QueryMapping
  public List<JobCategory> searchJobCategories() {
    
    return jobCategoryService.fetch();
  }

  @MutationMapping
  public JobCategory addJobCategory(@Argument UUID id, @Argument String name) {
    return jobCategoryService.addJobCategory(id,name);
  }

  @MutationMapping
  public Boolean deleteJobCategory(@Argument UUID id) {
    return jobCategoryService.deleteJobCategory(id);
  }

}
