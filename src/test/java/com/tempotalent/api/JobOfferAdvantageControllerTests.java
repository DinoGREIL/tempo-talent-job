package test.java.com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.tierfeature.TierFeature;

class JobOfferAdvantageControllerTests extends AbstractTest {

  @Test
  void testAddDeleteAdvantageToJobOffer() {
    var query = tester.document(
        "mutation { addAdvantageToJobOffer(advantageId: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\", jobOfferId: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\") { advantage { id } jobOffer { id } } }");
    var results = query.execute().path("addAdvantageToJobOffer").entity(JobOfferAdvantage.class).get();
    query = tester.document("mutation delete { deleteAdvantagefromJobOffer(advantageId: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\", jobOfferId: \"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11\") }");
    var deleted = query.execute().path("deleteAdvantagefromJobOffer").entity(Boolean.class).get();

    

    assertTrue(deleted);
    assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), results.getJobOffer().getId());
    assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), results.getAdvantage().getId());
    
  }

  
}
