package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.MedicalRecordDTO;
import com.CV.Hospital.services.MedicalRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalRecordDTO addMedicalRecord(
            @Valid @RequestBody MedicalRecordDTO dto) {
        return medicalRecordService.addMedicalRecord(dto);
    }

    @GetMapping
    public List<MedicalRecordDTO> getAllMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/{id}")
    public MedicalRecordDTO getMedicalRecordById(
            @PathVariable Long id) {
        return medicalRecordService.getMedicalRecordById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalRecordDTO> getMedicalRecordsByPatient(
            @PathVariable Long patientId) {
        return medicalRecordService
                .getMedicalRecordsByPatient(patientId);
    }

    @PutMapping("/{id}")
    public MedicalRecordDTO updateMedicalRecord(
            @PathVariable Long id,
            @Valid @RequestBody MedicalRecordDTO dto) {
        return medicalRecordService.updateMedicalRecord(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
    }

    @GetMapping("/patient/{patientId}/history")
    public List<MedicalRecordDTO> getPatientMedicalRecords(
            @PathVariable Long patientId) {

        return medicalRecordService
                .getPatientMedicalRecords(patientId);
    }
}