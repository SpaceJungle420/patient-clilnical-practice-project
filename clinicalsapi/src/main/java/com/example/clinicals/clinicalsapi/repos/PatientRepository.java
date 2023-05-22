package com.example.clinicals.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clinicals.clinicalsapi.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
