package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Patient;
import com.CV.Hospital.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public Patient addPatient(
            @RequestBody Patient patient,
            @RequestParam Long hospitalId) {

        return patientService.addPatient(patient, hospitalId);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<Patient> getPatientsByHospital(
            @PathVariable Long hospitalId) {

        return patientService.getPatientsByHospital(hospitalId);
    }
}
