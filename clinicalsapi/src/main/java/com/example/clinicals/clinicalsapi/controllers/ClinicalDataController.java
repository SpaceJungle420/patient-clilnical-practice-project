package com.example.clinicals.clinicalsapi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinicals.clinicalsapi.dto.ClinicalDataRequest;
import com.example.clinicals.clinicalsapi.model.ClinicalData;
import com.example.clinicals.clinicalsapi.model.Patient;
import com.example.clinicals.clinicalsapi.repos.ClinicalDataRepository;
import com.example.clinicals.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api")
public class ClinicalDataController {
    private ClinicalDataRepository clinicalDataRepository;
    private PatientRepository patientRepository;

    @Autowired
    ClinicalDataController(ClinicalDataRepository clinicalDataRepository, PatientRepository patientRepository) {
        this.clinicalDataRepository = clinicalDataRepository;
        this.patientRepository = patientRepository;
    }

    @RequestMapping(value = "/clinicals", method = RequestMethod.POST)
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId()).get();
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setComponentName(request.getComponentName());
        clinicalData.setComponentValue(request.getComponentValue());
        clinicalData.setPatient(patient);
        return clinicalDataRepository.save(clinicalData);
    }
}
