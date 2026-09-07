package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Doctor;
import com.CV.Hospital.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
