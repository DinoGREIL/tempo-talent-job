package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.api.models.Advantage;

@SpringBootTest
@AutoConfigureGraphQlTester
class AdvantageControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String createQuery = "mutation addAdvantage($id:ID, $name:String){ addAdvantage(id:$id,name:$name) { name  } }";

  @Test
  void searchAdvantages() {
    var query = tester.document("query { companies { siret name } }");
    var results = query.execute().path("searchAdvantages").entityList(Advantage.class);

    assertTrue(results.get().size() > 0);
  }

  

  

  private Advantage addAdvantage() {
    var query = tester.document(createQuery);
    return query
        .variable("name", "Test Advantage input")
        .variable("id", 12).execute().path("addAdvantage")
        .entity(Advantage.class).get();
  }

  

  private Boolean deleteAdvantage(int id) {
    var query = tester.document("mutation deleteAdvantage($id: ID!) {deleteAdvantage(id: $id)}");
    return query.variable("id", id).execute().path("deleteAdvantage").entity(Boolean.class).get();
  }

  @Test
  @Transactional
  void testAddDeleteAdvantage() {
    var advantage = addAdvantage();
    var deleted = deleteAdvantage(advantage.getId());

    assertEquals("Test Advantage input", advantage.getName());
    assertTrue(deleted);
  }

 
}