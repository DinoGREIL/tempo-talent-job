package com.tempotalent.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.JobCategory;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory, UUID> { 
}
