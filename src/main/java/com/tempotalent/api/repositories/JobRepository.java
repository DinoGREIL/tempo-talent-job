package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import com.tempotalent.api.models.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> { 
}
