package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository
        extends JpaRepository<Patient, Long> {
    List<Patient> findByIsActiveTrue();
    Optional<Patient> findByIdAndIsActiveTrue(Long id);
    List<Patient> findByHospitalIdAndIsActiveTrue(Long hospitalId);
}
