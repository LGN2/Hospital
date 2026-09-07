package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Doctor;
import com.CV.Hospital.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public Doctor addDoctor(
            @RequestBody Doctor doctor,
            @RequestParam Long departmentId,
            @RequestParam Long hospitalId) {
        return doctorService.addDoctor(
                doctor,
                departmentId,
                hospitalId
        );
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Doctor> getDoctorsByDepartment(
            @PathVariable Long departmentId) {

        return doctorService.getDoctorsByDepartment(departmentId);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<Doctor> getDoctorsByHospital(
            @PathVariable Long hospitalId) {

        return doctorService.getDoctorsByHospital(hospitalId);
    }
}
