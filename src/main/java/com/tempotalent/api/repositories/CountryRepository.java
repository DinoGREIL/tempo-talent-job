package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {}
