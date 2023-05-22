package com.example.clinicals.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clinicals.clinicalsapi.model.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

}
