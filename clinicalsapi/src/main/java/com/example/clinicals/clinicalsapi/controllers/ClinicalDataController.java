package com.example.clinicals.clinicalsapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinicals.clinicalsapi.dto.ClinicalDataRequest;
import com.example.clinicals.clinicalsapi.model.ClinicalData;
import com.example.clinicals.clinicalsapi.model.Patient;
import com.example.clinicals.clinicalsapi.repos.ClinicalDataRepository;
import com.example.clinicals.clinicalsapi.repos.PatientRepository;

import com.example.clinicals.clinicalsapi.util.BMICalculator;

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

    @RequestMapping(value = "/clinicals/{patientId}/{componentName}", method = RequestMethod.GET)
    public List<ClinicalData> getClinicalDate(
            @PathVariable("patientId") int patientId,
            @PathVariable("componentName") String componentName) {

        if (componentName.equals("bmi")) {
            componentName = "hw";
        }
        List<ClinicalData> clinicalData = clinicalDataRepository
                .findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);

        ArrayList<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
        for (ClinicalData eachEntry : duplicateClinicalData) {
            BMICalculator.calculateBMI(clinicalData, eachEntry);
        }
        return clinicalData;
    }
}
