package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.MedicalRecord;
import com.CV.Hospital.services.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
