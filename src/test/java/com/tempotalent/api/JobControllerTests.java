package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.api.models.Job;

@SpringBootTest
@AutoConfigureGraphQlTester
class JobControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String createQuery = "mutation addJob($id:ID,$title:String, $categoryid:ID){ addJob(id:$id,title:$title, categoryid:$categoryid) { id title  } }";

  @Test
  void searchJobs() {
    var query = tester.document("query { searchJobs { title  } }");
    var results = query.execute().path("searchJobs").entityList(Job.class);

    assertTrue(results.get().size() > 0);
  }

  

  

  private Job addJob() {
    var query = tester.document(createQuery);
    return  query
    .variable("id","b036db18-e4d3-446f-b5d6-627ea896dd89")
        .variable("title", "test job input")
        .variable("categoryid","a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
        .execute().path("addJob")
        .entity(Job.class).get();
  }

  

  private Boolean deleteJob(UUID uuid) {
    var query = tester.document("mutation deleteJob($id: ID!) {deleteJob(id: $id)}");
    return query.variable("id", uuid).execute().path("deleteJob").entity(Boolean.class).get();
  }

  @Test
  @Transactional
  void testAddDeleteJob() {
    var job = addJob();
    var deleted = deleteJob(job.getId());

    assertEquals("test job input", job.getTitle());
    assertTrue(deleted);
  }

 
}