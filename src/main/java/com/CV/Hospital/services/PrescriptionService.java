package com.CV.Hospital.services;

import com.CV.Hospital.entities.MedicalRecord;
import com.CV.Hospital.entities.Prescription;
import com.CV.Hospital.repositories.MedicalRecordRepository;
import com.CV.Hospital.repositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public Prescription addPrescription(
            Prescription prescription,
            Long medicalRecordId) {
        MedicalRecord medicalRecord =
                medicalRecordRepository
                        .findByIdAndIsActiveTrue(medicalRecordId)
                        .orElseThrow(() ->
                                new RuntimeException("Medical record not found"));
        prescription.setMedicalRecord(medicalRecord);
        prescription.setIsActive(true);
        return prescriptionRepository.save(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findByIsActiveTrue();
    }

    public Prescription getPrescriptionById(Long id) {
        return prescriptionRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Prescription not found"));
    }

    public List<Prescription> getPrescriptionsByMedicalRecord(
            Long medicalRecordId) {
        return prescriptionRepository
                .findByMedicalRecordIdAndIsActiveTrue(medicalRecordId);
    }

    public Prescription updatePrescription(
            Long id,
            Prescription updatedPrescription) {
        Prescription prescription = getPrescriptionById(id);
        if (updatedPrescription.getMedicineName() != null) {
            prescription.setMedicineName(
                    updatedPrescription.getMedicineName()
            );
        }
        if (updatedPrescription.getDosage() != null) {
            prescription.setDosage(updatedPrescription.getDosage());
        }
        if (updatedPrescription.getDurationDays() != null) {
            prescription.setDurationDays(
                    updatedPrescription.getDurationDays()
            );
        }
        return prescriptionRepository.save(prescription);
    }
}
