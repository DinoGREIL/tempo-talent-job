package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.Tier;

@Repository
public interface TierRepository extends JpaRepository<Tier, Integer> { 
}
