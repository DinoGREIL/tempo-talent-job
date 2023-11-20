package com.tempotalent.api.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tempotalent.api.models.JobOffer;
import com.tempotalent.api.models.Advantage;
import com.tempotalent.api.models.JobOfferAdvantage;
import com.tempotalent.api.models.JobOfferAdvantageKey;
import com.tempotalent.api.repositories.JobOfferAdvantageRepository;

@Service
public class JobOfferAdvantageService {
  private final JobOfferAdvantageRepository repository;

  public JobOfferAdvantageService(JobOfferAdvantageRepository repository) {
    this.repository = repository;
  }

  public JobOfferAdvantage addAdvantageToJobOffer(UUID idadvantage, UUID idjob_offer) {
    var jobOfferAdvantage = new JobOfferAdvantage(idadvantage, idjob_offer);
    jobOfferAdvantage.setAdvantage(new Advantage());
    jobOfferAdvantage.setJobOffer(new JobOffer());
    jobOfferAdvantage.getAdvantage().setId(idadvantage);
    jobOfferAdvantage.getJobOffer().setId(idjob_offer);
    return repository.save(jobOfferAdvantage);
  }

  public Boolean removeAdvantageFromJobOffer(UUID idadvantage, UUID idjob_offer) {
    var key = new JobOfferAdvantageKey(idjob_offer, idadvantage);
    try {
      repository.deleteById(key);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
