package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.DoctorDTO;
import com.CV.Hospital.services.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDTO addDoctor(
            @Valid @RequestBody DoctorDTO dto) {
        return doctorService.addDoctor(dto);
    }

    @GetMapping
    public List<DoctorDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<DoctorDTO> getDoctorsByDepartment(
            @PathVariable Long departmentId) {
        return doctorService.getDoctorsByDepartment(departmentId);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<DoctorDTO> getDoctorsByHospital(
            @PathVariable Long hospitalId) {
        return doctorService.getDoctorsByHospital(hospitalId);
    }

    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorDTO dto) {
        return doctorService.updateDoctor(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
}