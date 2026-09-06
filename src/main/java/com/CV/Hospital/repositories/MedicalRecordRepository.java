package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalRecordRepository
        extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByIsActiveTrue();
    Optional<MedicalRecord> findByIdAndIsActiveTrue(Long id);
    List<MedicalRecord> findByPatientIdAndIsActiveTrue(Long patientId);
}