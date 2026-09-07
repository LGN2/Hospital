package com.CV.Hospital.controllers;

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
    public Appointment addAppointment(
            @RequestBody Appointment appointment,
            @RequestParam Long doctorId,
            @RequestParam Long patientId) {
        return appointmentService.addAppointment(
                appointment,
                doctorId,
                patientId
        );
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(
            @PathVariable Long doctorId) {

        return appointmentService.getAppointmentsByDoctor(doctorId);
    }
}
