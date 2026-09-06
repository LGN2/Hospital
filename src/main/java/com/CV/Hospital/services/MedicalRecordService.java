package com.CV.Hospital.services;

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

    public MedicalRecord addMedicalRecord(
            MedicalRecord medicalRecord,
            Long patientId) {
        Patient patient =
                patientRepository.findByIdAndIsActiveTrue(patientId)
                        .orElseThrow(() ->
                                new RuntimeException("Patient not found"));

        medicalRecord.setPatient(patient);
        medicalRecord.setIsActive(true);
        return medicalRecordRepository.save(medicalRecord);
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findByIsActiveTrue();
    }
}
