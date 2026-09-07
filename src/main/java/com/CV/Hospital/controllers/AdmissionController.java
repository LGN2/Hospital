package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Admission;
import com.CV.Hospital.services.AdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService admissionService;

    @PostMapping
    public Admission admitPatient(
            @RequestBody Admission admission,
            @RequestParam Long patientId,
            @RequestParam Long roomId) {

        return admissionService.admitPatient(
                admission,
                patientId,
                roomId
        );
    }

}
