package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    List<Appointment> findByIsActiveTrue();

    Optional<Appointment> findByIdAndIsActiveTrue(Long id);

    List<Appointment> findByDoctorIdAndIsActiveTrue(Long doctorId);

    List<Appointment> findByPatientIdAndIsActiveTrue(Long patientId);

    @Query("""
            SELECT a
            FROM Appointment a
            WHERE a.doctor.id = :doctorId
            AND a.isActive = true
            AND a.appointmentDate >= :startDate
            AND a.appointmentDate < :endDate
            """)
    List<Appointment> findActiveAppointmentsForDoctorOnDate(
            @Param("doctorId") Long doctorId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("""
            SELECT COUNT(a)
            FROM Appointment a
            WHERE a.doctor.id = :doctorId
            AND a.isActive = true
            """)
    Long countActiveAppointmentsByDoctor(
            @Param("doctorId") Long doctorId
    );
}