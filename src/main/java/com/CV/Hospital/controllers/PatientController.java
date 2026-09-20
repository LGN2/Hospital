package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.PatientDTO;
import com.CV.Hospital.services.PatientService;
import jakarta.validation.Valid;
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
            @Valid @RequestBody PatientDTO dto) {
        return patientService.addPatient(dto);
    }

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<PatientDTO> getPatientsByHospital(
            @PathVariable Long hospitalId) {
        return patientService.getPatientsByHospital(hospitalId);
    }

    @PutMapping("/{id}")
    public PatientDTO updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientDTO dto) {
        return patientService.updatePatient(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}