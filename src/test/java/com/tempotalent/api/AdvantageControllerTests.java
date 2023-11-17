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

import com.tempotalent.api.models.Advantage;

@SpringBootTest
@AutoConfigureGraphQlTester
class AdvantageControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String createQuery = "mutation addAdvantage($id:ID, $name:String){ addAdvantage(id:$id,name:$name) {id name  } }";

  @Test
  void searchAdvantages() {
    var query = tester.document("query { searchAdvantages { id name } }");
    var results = query.execute().path("searchAdvantages").entityList(Advantage.class);

    assertTrue(results.get().size() > 0);
  }
  @Test
  @Transactional
  void testAdvantageById() {
    var query = tester.document(
        "query { advantageById(id: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\") { id  } }");
    var result = query.execute().path("advantageById").entity(Advantage.class).get();

    assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), result.getId());
  }

  private Advantage addAdvantage() {
    var query = tester.document(createQuery);
    return query
        .variable("id", "8e04fb60-9e21-4781-9246-2df648f7c1f9")
        .variable("name", "Test Advantage input")
        .execute().path("addAdvantage")
        .entity(Advantage.class).get();
  }

  

  private Boolean deleteAdvantage(UUID id) {
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