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

  public JobOfferAdvantage addAdvantageToJobOffer(UUID advantageId, UUID jobOfferId) {
    var jobOfferAdvantage = new JobOfferAdvantage(advantageId, jobOfferId);
    jobOfferAdvantage.setAdvantage(new Advantage());
    jobOfferAdvantage.setJobOffer(new JobOffer());
    jobOfferAdvantage.getAdvantage().setId(advantageId);
    jobOfferAdvantage.getJobOffer().setId(jobOfferId);
    return repository.save(jobOfferAdvantage);
  }

  public Boolean removeAdvantageFromJobOffer(UUID advantageId, UUID jobOfferId) {
    var key = new JobOfferAdvantageKey(jobOfferId, advantageId);
    try {
      repository.deleteById(key);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
