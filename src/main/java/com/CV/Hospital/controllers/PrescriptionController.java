package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Prescription;
import com.CV.Hospital.services.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public Prescription getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @GetMapping("/medical-record/{medicalRecordId}")
    public List<Prescription> getByMedicalRecord(
            @PathVariable Long medicalRecordId) {

        return prescriptionService
                .getPrescriptionsByMedicalRecord(medicalRecordId);
    }

    @PutMapping("/{id}")
    public Prescription updatePrescription(
            @PathVariable Long id,
            @RequestBody Prescription prescription) {

        return prescriptionService.updatePrescription(
                id,
                prescription
        );
    }
}
