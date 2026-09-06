package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository
        extends JpaRepository<Prescription, Long> {
    List<Prescription> findByIsActiveTrue();
    Optional<Prescription> findByIdAndIsActiveTrue(Long id);
    List<Prescription> findByMedicalRecordIdAndIsActiveTrue(Long medicalRecordId);
}