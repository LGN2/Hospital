package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Hospital;
import com.CV.Hospital.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public Hospital addHospital(@RequestBody Hospital hospital) {
        return hospitalService.addHospital(hospital);
    }
}
