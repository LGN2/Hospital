package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.DoctorDTO;
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
    public DoctorDTO addDoctor(
            @RequestBody Doctor doctor,
            @RequestParam Long departmentId,
            @RequestParam Long hospitalId) {

        return doctorService.convertToDTO(
                doctorService.addDoctor(
                        doctor,
                        departmentId,
                        hospitalId
                )
        );
    }

    @GetMapping
    public List<DoctorDTO> getAllDoctors() {
        return doctorService.convertToDTO(
                doctorService.getAllDoctors()
        );
    }

    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id) {
        return doctorService.convertToDTO(
                doctorService.getDoctorById(id)
        );
    }

    @GetMapping("/department/{departmentId}")
    public List<DoctorDTO> getDoctorsByDepartment(
            @PathVariable Long departmentId) {

        return doctorService.convertToDTO(
                doctorService.getDoctorsByDepartment(departmentId)
        );
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<DoctorDTO> getDoctorsByHospital(
            @PathVariable Long hospitalId) {

        return doctorService.convertToDTO(
                doctorService.getDoctorsByHospital(hospitalId)
        );
    }

    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {

        return doctorService.convertToDTO(
                doctorService.updateDoctor(id, doctor)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
}