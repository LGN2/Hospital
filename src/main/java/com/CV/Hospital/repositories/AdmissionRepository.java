package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdmissionRepository
        extends JpaRepository<Admission, Long> {

    List<Admission> findByIsActiveTrue();

    Optional<Admission> findByIdAndIsActiveTrue(Long id);

    List<Admission> findByPatientIdAndIsActiveTrue(
            Long patientId
    );

    List<Admission> findByRoomIdAndIsActiveTrue(
            Long roomId
    );

    List<Admission>
    findByRoomIdAndDischargeDateIsNullAndIsActiveTrue(
            Long roomId
    );

    @Query("""
            SELECT a
            FROM Admission a
            WHERE a.room.floor = :floor
            AND a.dischargeDate IS NULL
            AND a.isActive = true
            AND a.patient.isActive = true
            """)
    List<Admission> findActiveAdmissionsByFloor(
            @Param("floor") Integer floor
    );
}