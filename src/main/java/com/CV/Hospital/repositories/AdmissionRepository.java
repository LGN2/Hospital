package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdmissionRepository
        extends JpaRepository<Admission, Long> {
    List<Admission> findByIsActiveTrue();
    Optional<Admission> findByIdAndIsActiveTrue(Long id);
    List<Admission> findByPatientIdAndIsActiveTrue(Long patientId);
    List<Admission> findByRoomIdAndIsActiveTrue(Long roomId);
    List<Admission> findByRoomIdAndDischargeDateIsNullAndIsActiveTrue(Long roomId);
}