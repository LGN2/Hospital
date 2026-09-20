package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.MedicalRecordDTO;
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
    public MedicalRecordDTO addMedicalRecord(
            @RequestBody MedicalRecord medicalRecord,
            @RequestParam Long patientId) {

        return medicalRecordService.convertToDTO(
                medicalRecordService.addMedicalRecord(
                        medicalRecord,
                        patientId
                )
        );
    }

    @GetMapping
    public List<MedicalRecordDTO> getAllMedicalRecords() {
        return medicalRecordService.convertToDTO(
                medicalRecordService.getAllMedicalRecords()
        );
    }

    @GetMapping("/{id}")
    public MedicalRecordDTO getMedicalRecordById(
            @PathVariable Long id) {

        return medicalRecordService.convertToDTO(
                medicalRecordService.getMedicalRecordById(id)
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalRecordDTO> getMedicalRecordsByPatient(
            @PathVariable Long patientId) {

        return medicalRecordService.convertToDTO(
                medicalRecordService.getMedicalRecordsByPatient(patientId)
        );
    }

    @PutMapping("/{id}")
    public MedicalRecordDTO updateMedicalRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecord medicalRecord) {

        return medicalRecordService.convertToDTO(
                medicalRecordService.updateMedicalRecord(
                        id,
                        medicalRecord
                )
        );
    }

    @DeleteMapping("/{id}")
    public void deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
    }
}