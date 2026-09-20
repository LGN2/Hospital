package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.AppointmentDTO;
import com.CV.Hospital.entities.Appointment;
import com.CV.Hospital.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public AppointmentDTO addAppointment(
            @RequestBody Appointment appointment,
            @RequestParam Long doctorId,
            @RequestParam Long patientId) {

        return appointmentService.convertToDTO(
                appointmentService.addAppointment(
                        appointment,
                        doctorId,
                        patientId
                )
        );
    }

    @GetMapping
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentService.convertToDTO(
                appointmentService.getAllAppointments()
        );
    }

    @GetMapping("/{id}")
    public AppointmentDTO getAppointmentById(@PathVariable Long id) {
        return appointmentService.convertToDTO(
                appointmentService.getAppointmentById(id)
        );
    }

    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentDTO> getAppointmentsByDoctor(
            @PathVariable Long doctorId) {

        return appointmentService.convertToDTO(
                appointmentService.getAppointmentsByDoctor(doctorId)
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentDTO> getAppointmentsByPatient(
            @PathVariable Long patientId) {

        return appointmentService.convertToDTO(
                appointmentService.getAppointmentsByPatient(patientId)
        );
    }

    @PutMapping("/{id}")
    public AppointmentDTO updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment appointment) {

        return appointmentService.convertToDTO(
                appointmentService.updateAppointment(id, appointment)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}