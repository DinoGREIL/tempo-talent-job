package com.tempotalent.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.Recruiter;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, UUID> {
}
