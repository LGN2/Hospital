package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalRecordRepository
        extends JpaRepository<MedicalRecord, Long> {

    List<MedicalRecord> findByIsActiveTrue();

    Optional<MedicalRecord> findByIdAndIsActiveTrue(Long id);

    List<MedicalRecord> findByPatientIdAndIsActiveTrue(
            Long patientId
    );

    @Query("""
            SELECT m
            FROM MedicalRecord m
            WHERE m.patient.id = :patientId
            AND m.isActive = true
            ORDER BY m.recordDate DESC
            """)
    List<MedicalRecord> findAllActiveMedicalRecordsForPatient(
            @Param("patientId") Long patientId
    );
}