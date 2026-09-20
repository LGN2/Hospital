package com.CV.Hospital.services;

import com.CV.Hospital.dto.MedicalRecordDTO;
import com.CV.Hospital.entities.MedicalRecord;
import com.CV.Hospital.entities.Patient;
import com.CV.Hospital.repositories.MedicalRecordRepository;
import com.CV.Hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    public MedicalRecordDTO addMedicalRecord(MedicalRecordDTO dto) {

        Patient patient = patientRepository
                .findByIdAndIsActiveTrue(dto.getPatientId())
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        MedicalRecord record = new MedicalRecord();

        record.setDiagnosis(dto.getDiagnosis());
        record.setNotes(dto.getNotes());
        record.setRecordDate(dto.getRecordDate());
        record.setPatient(patient);

        return convertToDTO(
                medicalRecordRepository.save(record)
        );
    }

    public List<MedicalRecordDTO> getAllMedicalRecords() {
        return convertToDTO(
                medicalRecordRepository.findByIsActiveTrue()
        );
    }

    public MedicalRecordDTO getMedicalRecordById(Long id) {
        return convertToDTO(findActiveMedicalRecord(id));
    }

    public List<MedicalRecordDTO> getMedicalRecordsByPatient(
            Long patientId) {

        return convertToDTO(
                medicalRecordRepository
                        .findByPatientIdAndIsActiveTrue(patientId)
        );
    }

    public MedicalRecordDTO updateMedicalRecord(
            Long id,
            MedicalRecordDTO dto) {

        MedicalRecord record = findActiveMedicalRecord(id);

        Patient patient = patientRepository
                .findByIdAndIsActiveTrue(dto.getPatientId())
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        record.setDiagnosis(dto.getDiagnosis());
        record.setNotes(dto.getNotes());
        record.setRecordDate(dto.getRecordDate());
        record.setPatient(patient);

        return convertToDTO(
                medicalRecordRepository.save(record)
        );
    }

    public void deleteMedicalRecord(Long id) {
        MedicalRecord record = findActiveMedicalRecord(id);
        record.setIsActive(false);
        medicalRecordRepository.save(record);
    }

    private MedicalRecord findActiveMedicalRecord(Long id) {
        return medicalRecordRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Medical record not found"));
    }

    public MedicalRecordDTO convertToDTO(MedicalRecord record) {
        return MedicalRecordDTO.builder()
                .id(record.getId())
                .diagnosis(record.getDiagnosis())
                .notes(record.getNotes())
                .recordDate(record.getRecordDate())
                .patientId(record.getPatient().getId())
                .build();
    }

    public List<MedicalRecordDTO> convertToDTO(
            List<MedicalRecord> records) {

        return records.stream()
                .map(this::convertToDTO)
                .toList();
    }
}