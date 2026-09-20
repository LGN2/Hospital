package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.PatientDTO;
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
    public PatientDTO addPatient(
            @RequestBody Patient patient,
            @RequestParam Long hospitalId) {

        return patientService.convertToDTO(
                patientService.addPatient(patient, hospitalId)
        );
    }

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.convertToDTO(
                patientService.getAllPatients()
        );
    }

    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        return patientService.convertToDTO(
                patientService.getPatientById(id)
        );
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<PatientDTO> getPatientsByHospital(
            @PathVariable Long hospitalId) {

        return patientService.convertToDTO(
                patientService.getPatientsByHospital(hospitalId)
        );
    }

    @PutMapping("/{id}")
    public PatientDTO updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {

        return patientService.convertToDTO(
                patientService.updatePatient(id, patient)
        );
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}