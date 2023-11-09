package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {}
