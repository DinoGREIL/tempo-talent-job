package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.JobOffer;
import java.util.UUID;
@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> { 
}
