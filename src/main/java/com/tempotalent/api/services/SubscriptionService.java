package com.tempotalent.api.services;

import java.time.LocalDate;

import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.tempotalent.api.exceptions.InvalidInput;
import com.tempotalent.api.models.Recruiter;
import com.tempotalent.api.models.Subscription;
import com.tempotalent.api.models.SubscriptionKey;
import com.tempotalent.api.models.Tier;
import com.tempotalent.api.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
  private final SubscriptionRepository repository;

  public SubscriptionService(SubscriptionRepository repository) {
    this.repository = repository;
  }

  public Subscription addSubscriptionToRecruiter(UUID recruiterId, Integer tierId, LocalDate startDate,
      LocalDate enDate) {
    var sub = new Subscription();
    sub.setRecruiterId(recruiterId);
    var example = Example.of(sub);
    var existing = repository.findOne(example);
    sub.setTierId(tierId);
    sub.setStartDate(startDate);
    sub.setEndDate(enDate);
    sub.setRecruiter(new Recruiter());
    sub.getRecruiter().setId(recruiterId);
    sub.setTier(new Tier());
    sub.getTier().setId(tierId);

    return existing.isEmpty()
        ? repository.save(sub)
        : existing
            .filter(s -> s.getEndDate().isBefore(startDate) || s.getEndDate().equals(startDate))
            .map(repository::save)
            .orElseThrow(() -> new InvalidInput("Existing valid subscription"));
  }

  public Boolean rmSubscriptionFromRecruiter(UUID recruiterId, Integer tierId) {
    repository.deleteById(new SubscriptionKey(recruiterId, tierId));
    return true;
  }
}
