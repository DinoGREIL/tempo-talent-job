package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.TierFeature;
import com.tempotalent.api.models.TierFeatureKey;

@Repository
public interface TierFeatureRepository extends JpaRepository<TierFeature, TierFeatureKey> {
}
