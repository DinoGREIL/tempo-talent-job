package com.tempotalent.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.JobOfferAdvantage;

import com.tempotalent.api.models.JobOfferAdvantageKey;

@Repository
public interface JobOfferAdvantageRepository extends JpaRepository<JobOfferAdvantage, UUID> {
}
