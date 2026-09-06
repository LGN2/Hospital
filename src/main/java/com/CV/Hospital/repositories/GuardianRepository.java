package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuardianRepository
        extends JpaRepository<Guardian, Long> {
    List<Guardian> findByIsActiveTrue();
    Optional<Guardian> findByIdAndIsActiveTrue(Long id);
    List<Guardian> findByPatientIdAndIsActiveTrue(Long patientId);
}