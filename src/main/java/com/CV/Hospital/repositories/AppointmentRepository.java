package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {
    List<Appointment> findByIsActiveTrue();
    Optional<Appointment> findByIdAndIsActiveTrue(Long id);
    List<Appointment> findByDoctorIdAndIsActiveTrue(Long doctorId);
    List<Appointment> findByPatientIdAndIsActiveTrue(Long patientId);
}