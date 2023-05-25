package com.example.clinicals.clinicalsapi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinicals.clinicalsapi.model.ClinicalData;
import com.example.clinicals.clinicalsapi.model.Patient;
import com.example.clinicals.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api")
public class PatientController {

    private PatientRepository repository;
    Map<String, String> filters = new HashMap<>();

    @Autowired
    PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public List<Patient> getPatients() {
        return repository.findAll();
    }

    @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
    public Patient getPatient(@PathVariable("id") int id) {
        return repository.findById(id).get();
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public Patient savePatient(@RequestBody Patient patient) {
        return repository.save(patient);
    }

    @RequestMapping(value = "/patients/analyze/{id}", method = RequestMethod.GET)
    public Patient analyze(@PathVariable("id") int id) {
        Patient patient = repository.findById(id).get();
        List<ClinicalData> clinicalData = patient.getClinicalData();
        ArrayList<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);

        for (ClinicalData eachEntry : duplicateClinicalData) {
            if (eachEntry.getComponentName().equals("hw")) {
                String[] heightAndWeight = eachEntry.getComponentValue().split("/");
                if (filters.containsKey(eachEntry.getComponentName())) {
                    clinicalData.remove(eachEntry);
                    continue;
                } else {
                    filters.put(eachEntry.getComponentName(), null);
                }

                if (eachEntry.getComponentName().equals("hw")) {
                    if (heightAndWeight != null && heightAndWeight.length > 1) {
                        float heightInMeters = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
                        float bmi = Float.parseFloat(heightAndWeight[1]) / (heightInMeters * heightInMeters);

                        ClinicalData bmiData = new ClinicalData();
                        bmiData.setComponentValue(Float.toString(bmi));
                        clinicalData.add(bmiData);
                    }
                }
            }
        }
        filters.clear();
        return patient;
    }
}
