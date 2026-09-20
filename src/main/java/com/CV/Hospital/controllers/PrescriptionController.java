package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.PrescriptionDTO;
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
    public PrescriptionDTO addPrescription(
            @RequestBody Prescription prescription,
            @RequestParam Long medicalRecordId) {

        return prescriptionService.convertToDTO(
                prescriptionService.addPrescription(
                        prescription,
                        medicalRecordId
                )
        );
    }

    @GetMapping
    public List<PrescriptionDTO> getAllPrescriptions() {
        return prescriptionService.convertToDTO(
                prescriptionService.getAllPrescriptions()
        );
    }

    @GetMapping("/{id}")
    public PrescriptionDTO getPrescriptionById(
            @PathVariable Long id) {

        return prescriptionService.convertToDTO(
                prescriptionService.getPrescriptionById(id)
        );
    }

    @GetMapping("/medical-record/{medicalRecordId}")
    public List<PrescriptionDTO> getByMedicalRecord(
            @PathVariable Long medicalRecordId) {

        return prescriptionService.convertToDTO(
                prescriptionService
                        .getPrescriptionsByMedicalRecord(medicalRecordId)
        );
    }

    @PutMapping("/{id}")
    public PrescriptionDTO updatePrescription(
            @PathVariable Long id,
            @RequestBody Prescription prescription) {

        return prescriptionService.convertToDTO(
                prescriptionService.updatePrescription(id, prescription)
        );
    }

    @DeleteMapping("/{id}")
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
    }
}