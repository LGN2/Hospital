package com.CV.Hospital.services;

import com.CV.Hospital.dto.AppointmentDTO;
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

    public AppointmentDTO addAppointment(AppointmentDTO dto) {

        Doctor doctor = doctorRepository
                .findByIdAndIsActiveTrue(dto.getDoctorId())
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found"));

        Patient patient = patientRepository
                .findByIdAndIsActiveTrue(dto.getPatientId())
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        validateDate(dto.getAppointmentDate());

        Appointment appointment = new Appointment();

        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setReason(dto.getReason());
        appointment.setStatus(dto.getStatus());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return convertToDTO(
                appointmentRepository.save(appointment)
        );
    }

    public List<AppointmentDTO> getAllAppointments() {
        return convertToDTO(
                appointmentRepository.findByIsActiveTrue()
        );
    }

    public AppointmentDTO getAppointmentById(Long id) {
        return convertToDTO(findActiveAppointment(id));
    }

    public List<AppointmentDTO> getAppointmentsByDoctor(
            Long doctorId) {

        return convertToDTO(
                appointmentRepository
                        .findByDoctorIdAndIsActiveTrue(doctorId)
        );
    }

    public List<AppointmentDTO> getAppointmentsByPatient(
            Long patientId) {

        return convertToDTO(
                appointmentRepository
                        .findByPatientIdAndIsActiveTrue(patientId)
        );
    }

    public AppointmentDTO updateAppointment(
            Long id,
            AppointmentDTO dto) {

        Appointment appointment = findActiveAppointment(id);

        validateDate(dto.getAppointmentDate());

        Doctor doctor = doctorRepository
                .findByIdAndIsActiveTrue(dto.getDoctorId())
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found"));

        Patient patient = patientRepository
                .findByIdAndIsActiveTrue(dto.getPatientId())
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setReason(dto.getReason());
        appointment.setStatus(dto.getStatus());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return convertToDTO(
                appointmentRepository.save(appointment)
        );
    }

    public void deleteAppointment(Long id) {
        Appointment appointment = findActiveAppointment(id);
        appointment.setIsActive(false);
        appointmentRepository.save(appointment);
    }

    private Appointment findActiveAppointment(Long id) {
        return appointmentRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Appointment not found"));
    }

    private void validateDate(LocalDateTime date) {
        if (date == null || !date.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Appointment date must be in the future"
            );
        }
    }

    public AppointmentDTO convertToDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .appointmentDate(appointment.getAppointmentDate())
                .reason(appointment.getReason())
                .status(appointment.getStatus())
                .doctorId(appointment.getDoctor().getId())
                .patientId(appointment.getPatient().getId())
                .build();
    }

    public List<AppointmentDTO> convertToDTO(
            List<Appointment> appointments) {

        return appointments.stream()
                .map(this::convertToDTO)
                .toList();
    }
}