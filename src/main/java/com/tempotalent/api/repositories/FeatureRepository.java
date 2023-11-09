package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {}
