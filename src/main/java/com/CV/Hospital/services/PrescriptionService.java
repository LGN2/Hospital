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
}
