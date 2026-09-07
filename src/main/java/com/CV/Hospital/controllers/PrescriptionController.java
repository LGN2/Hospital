package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Prescription;
import com.CV.Hospital.services.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    public Prescription addPrescription(
            @RequestBody Prescription prescription,
            @RequestParam Long medicalRecordId) {

        return prescriptionService.addPrescription(
                prescription,
                medicalRecordId
        );
    }
}
