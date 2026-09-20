package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.AppointmentDTO;
import com.CV.Hospital.services.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDTO addAppointment(
            @Valid @RequestBody AppointmentDTO dto) {
        return appointmentService.addAppointment(dto);
    }

    @GetMapping
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentDTO getAppointmentById(
            @PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentDTO> getAppointmentsByDoctor(
            @PathVariable Long doctorId) {
        return appointmentService
                .getAppointmentsByDoctor(doctorId);
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentDTO> getAppointmentsByPatient(
            @PathVariable Long patientId) {
        return appointmentService
                .getAppointmentsByPatient(patientId);
    }

    @PutMapping("/{id}")
    public AppointmentDTO updateAppointment(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentDTO dto) {
        return appointmentService.updateAppointment(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
    @GetMapping("/doctor/{doctorId}/date/{date}")
    public List<AppointmentDTO> getDoctorAppointmentsByDate(
            @PathVariable Long doctorId,
            @PathVariable LocalDate date) {

        return appointmentService
                .getDoctorAppointmentsByDate(doctorId, date);
    }

    @GetMapping("/doctor/{doctorId}/count")
    public Long getTotalAppointmentsForDoctor(
            @PathVariable Long doctorId) {

        return appointmentService
                .getTotalAppointmentsForDoctor(doctorId);
    }
}