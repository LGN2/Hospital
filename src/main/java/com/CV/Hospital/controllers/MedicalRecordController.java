package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.MedicalRecord;
import com.CV.Hospital.services.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping
    public MedicalRecord addMedicalRecord(
            @RequestBody MedicalRecord medicalRecord,
            @RequestParam Long patientId) {

        return medicalRecordService.addMedicalRecord(
                medicalRecord,
                patientId
        );
    }

    @GetMapping
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/{id}")
    public MedicalRecord getMedicalRecordById(@PathVariable Long id) {
        return medicalRecordService.getMedicalRecordById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalRecord> getMedicalRecordsByPatient(
            @PathVariable Long patientId) {

        return medicalRecordService
                .getMedicalRecordsByPatient(patientId);
    }

    @PutMapping("/{id}")
    public MedicalRecord updateMedicalRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecord medicalRecord) {

        return medicalRecordService.updateMedicalRecord(
                id,
                medicalRecord
        );
    }

    @DeleteMapping("/{id}")
    public void deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
    }
}
