package com.tempotalent.api.services;

import org.springframework.stereotype.Service;

import com.tempotalent.api.models.Feature;
import com.tempotalent.api.models.Tier;
import com.tempotalent.api.models.TierFeature;
import com.tempotalent.api.models.TierFeatureKey;
import com.tempotalent.api.repositories.TierFeatureRepository;

@Service
public class TierFeatureService {
  private final TierFeatureRepository repository;

  public TierFeatureService(TierFeatureRepository repository) {
    this.repository = repository;
  }

  public TierFeature addFeatureToTier(Integer tierId, Integer featureId, String value) {
    var tierFeature = new TierFeature(tierId, featureId, value);
    tierFeature.setTier(new Tier());
    tierFeature.setFeature(new Feature());
    tierFeature.getTier().setId(tierId);
    tierFeature.getFeature().setId(featureId);
    return repository.save(tierFeature);
  }

  public Boolean removeFeatureFromTier(Integer tierId, Integer featureId) {
    var key = new TierFeatureKey(tierId, featureId);
    try {
      repository.deleteById(key);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
