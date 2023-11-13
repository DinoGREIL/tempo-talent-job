package com.tempotalent.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.Advantage;

@Repository
public interface AdvantageRepository extends JpaRepository<Advantage, UUID> { 
}
