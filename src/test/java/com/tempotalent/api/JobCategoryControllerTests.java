package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.api.models.JobCategory;

@SpringBootTest
@AutoConfigureGraphQlTester
class JobCategoryControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String createQuery = "mutation addJobCategory($id:ID, $name:String){ addJobCategory(id:$id,name:$name) { name  } }";

  @Test
  void searchJobCategories() {
    var query = tester.document("query { searchJobCategories { id name } }");
    var results = query.execute().path("searchJobCategories").entityList(JobCategory.class);

    assertTrue(results.get().size() > 0);
  }

  

  

  private JobCategory addJobCategory() {
    var query = tester.document(createQuery);
    return query
        .variable("name", "Test JobCategory input")
        .variable("id", 12).execute().path("addJobCategory")
        .entity(JobCategory.class).get();
  }

  

  private Boolean deleteJobCategory(int id) {
    var query = tester.document("mutation deleteJobCategory($id: ID!) {deleteJobCategory(id: $id)}");
    return query.variable("id", id).execute().path("deleteJobCategory").entity(Boolean.class).get();
  }

  @Test
  @Transactional
  void testAddDeleteJobCategory() {
    var jobcategory = addJobCategory();
    var deleted = deleteJobCategory(jobcategory.getId());

    assertEquals("Test JobCategory input", jobcategory.getName());
    assertTrue(deleted);
  }

 
}