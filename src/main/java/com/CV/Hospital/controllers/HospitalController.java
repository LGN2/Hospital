package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Hospital;
import com.CV.Hospital.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public Hospital addHospital(@RequestBody Hospital hospital) {
        return hospitalService.addHospital(hospital);
    }

    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/{id}")
    public Hospital getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id);
    }

    @PutMapping("/{id}")
    public Hospital updateHospital(
            @PathVariable Long id,
            @RequestBody Hospital hospital) {

        return hospitalService.updateHospital(id, hospital);
    }
}
