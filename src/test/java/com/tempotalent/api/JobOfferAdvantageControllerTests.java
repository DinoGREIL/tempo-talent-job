package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.models.JobOfferAdvantage;

class JobOfferAdvantageControllerTests extends AbstractTest {

  @Test
  void testAddDeleteAdvantageToJobOffer() {
    var query = tester.document("mutation removeAdvantagefromJobOffer($id:ID!) {removeAdvantagefromJobOffer(id:$id)}");
    var deleted = query
    .variable("id", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
    
    .execute().path("removeAdvantagefromJobOffer").entity(Boolean.class).get();
    query = tester.document(
        "mutation { addAdvantageToJobOffer(id:\"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\",idadvantage: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\", idjob_offer: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\") {id advantage { id } jobOffer { id } } }");
    var results = query.execute().path("addAdvantageToJobOffer").entity(JobOfferAdvantage.class).get();
    

    

    assertTrue(deleted);
    assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), results.getJobOffer().getId());
    assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), results.getAdvantage().getId());
    
  }

  
}
