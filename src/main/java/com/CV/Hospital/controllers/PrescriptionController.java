package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.PrescriptionDTO;
import com.CV.Hospital.services.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrescriptionDTO addPrescription(
            @Valid @RequestBody PrescriptionDTO dto) {
        return prescriptionService.addPrescription(dto);
    }

    @GetMapping
    public List<PrescriptionDTO> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public PrescriptionDTO getPrescriptionById(
            @PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @GetMapping("/medical-record/{medicalRecordId}")
    public List<PrescriptionDTO> getByMedicalRecord(
            @PathVariable Long medicalRecordId) {
        return prescriptionService
                .getPrescriptionsByMedicalRecord(medicalRecordId);
    }

    @PutMapping("/{id}")
    public PrescriptionDTO updatePrescription(
            @PathVariable Long id,
            @Valid @RequestBody PrescriptionDTO dto) {
        return prescriptionService.updatePrescription(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
    }
}