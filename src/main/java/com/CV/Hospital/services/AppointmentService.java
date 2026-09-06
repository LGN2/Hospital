package com.CV.Hospital.services;

import com.CV.Hospital.entities.Appointment;
import com.CV.Hospital.entities.Doctor;
import com.CV.Hospital.entities.Patient;
import com.CV.Hospital.repositories.AppointmentRepository;
import com.CV.Hospital.repositories.DoctorRepository;
import com.CV.Hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;


    public Appointment addAppointment(
            Appointment appointment,
            Long doctorId,
            Long patientId) {
        Doctor doctor = doctorRepository.findByIdAndIsActiveTrue(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findByIdAndIsActiveTrue(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        if (appointment.getAppointmentDate() == null) {
            throw new IllegalArgumentException("Appointment date is required");
        }
        if (appointment.getAppointmentDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Appointment date cannot be in the past"
            );
        }
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setIsActive(true);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findByIsActiveTrue();
    }
}
