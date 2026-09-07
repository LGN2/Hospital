package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Admission;
import com.CV.Hospital.services.AdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Admission> getAllAdmissions() {
        return admissionService.getAllAdmissions();
    }

    @GetMapping("/{id}")
    public Admission getAdmissionById(@PathVariable Long id) {
        return admissionService.getAdmissionById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<Admission> getAdmissionsByPatient(
            @PathVariable Long patientId) {

        return admissionService.getAdmissionsByPatient(patientId);
    }

    @GetMapping("/room/{roomId}")
    public List<Admission> getAdmissionsByRoom(
            @PathVariable Long roomId) {

        return admissionService.getAdmissionsByRoom(roomId);
    }

    @PutMapping("/{id}")
    public Admission updateAdmission(
            @PathVariable Long id,
            @RequestBody Admission admission) {

        return admissionService.updateAdmission(id, admission);
    }

}
