package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository
        extends JpaRepository<Patient, Long> {
    List<Patient> findByIsActiveTrue();
    Optional<Patient> findByIdAndIsActiveTrue(Long id);
    List<Patient> findByHospitalIdAndIsActiveTrue(Long hospitalId);

    @Query("""
        SELECT COUNT(p)
        FROM Patient p
        WHERE p.hospital.id = :hospitalId
        AND p.isActive = true
        """)
    Long countActivePatientsByHospital(
            @Param("hospitalId") Long hospitalId
    );
}
