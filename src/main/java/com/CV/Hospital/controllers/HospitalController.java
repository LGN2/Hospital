package com.CV.Hospital.controllers;

import com.CV.Hospital.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
}
