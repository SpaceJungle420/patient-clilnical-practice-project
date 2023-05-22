package com.example.clinicals.clinicalsapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinicals.clinicalsapi.model.Patient;
import com.example.clinicals.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api")
public class PatientController {

    private PatientRepository repository;

    @Autowired
    PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "patients", method = RequestMethod.GET)
    public List<Patient> getPatients() {
        return repository.findAll();
    }
}
