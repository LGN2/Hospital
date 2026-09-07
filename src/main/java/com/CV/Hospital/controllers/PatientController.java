package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Patient;
import com.CV.Hospital.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
