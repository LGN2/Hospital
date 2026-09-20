package com.CV.Hospital.services;

import com.CV.Hospital.dto.PrescriptionDTO;
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

    public PrescriptionDTO addPrescription(PrescriptionDTO dto) {

        MedicalRecord record = medicalRecordRepository
                .findByIdAndIsActiveTrue(dto.getMedicalRecordId())
                .orElseThrow(() ->
                        new RuntimeException("Medical record not found"));

        Prescription prescription = new Prescription();

        prescription.setMedicineName(dto.getMedicineName());
        prescription.setDosage(dto.getDosage());
        prescription.setDurationDays(dto.getDurationDays());
        prescription.setMedicalRecord(record);

        return convertToDTO(
                prescriptionRepository.save(prescription)
        );
    }

    public List<PrescriptionDTO> getAllPrescriptions() {
        return convertToDTO(
                prescriptionRepository.findByIsActiveTrue()
        );
    }

    public PrescriptionDTO getPrescriptionById(Long id) {
        return convertToDTO(findActivePrescription(id));
    }

    public List<PrescriptionDTO> getPrescriptionsByMedicalRecord(
            Long medicalRecordId) {

        return convertToDTO(
                prescriptionRepository
                        .findByMedicalRecordIdAndIsActiveTrue(
                                medicalRecordId
                        )
        );
    }

    public PrescriptionDTO updatePrescription(
            Long id,
            PrescriptionDTO dto) {

        Prescription prescription = findActivePrescription(id);

        MedicalRecord record = medicalRecordRepository
                .findByIdAndIsActiveTrue(dto.getMedicalRecordId())
                .orElseThrow(() ->
                        new RuntimeException("Medical record not found"));

        prescription.setMedicineName(dto.getMedicineName());
        prescription.setDosage(dto.getDosage());
        prescription.setDurationDays(dto.getDurationDays());
        prescription.setMedicalRecord(record);

        return convertToDTO(
                prescriptionRepository.save(prescription)
        );
    }

    public void deletePrescription(Long id) {
        Prescription prescription = findActivePrescription(id);
        prescription.setIsActive(false);
        prescriptionRepository.save(prescription);
    }

    private Prescription findActivePrescription(Long id) {
        return prescriptionRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Prescription not found"));
    }

    public PrescriptionDTO convertToDTO(Prescription prescription) {
        return PrescriptionDTO.builder()
                .id(prescription.getId())
                .medicineName(prescription.getMedicineName())
                .dosage(prescription.getDosage())
                .durationDays(prescription.getDurationDays())
                .medicalRecordId(
                        prescription.getMedicalRecord().getId()
                )
                .build();
    }

    public List<PrescriptionDTO> convertToDTO(
            List<Prescription> prescriptions) {

        return prescriptions.stream()
                .map(this::convertToDTO)
                .toList();
    }
}