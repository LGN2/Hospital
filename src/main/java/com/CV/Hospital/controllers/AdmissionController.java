package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.AdmissionDTO;
import com.CV.Hospital.services.AdmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService admissionService;

    @PostMapping
    public AdmissionDTO admitPatient(
            @Valid @RequestBody AdmissionDTO dto) {
        return admissionService.admitPatient(dto);
    }

    @GetMapping
    public List<AdmissionDTO> getAllAdmissions() {
        return admissionService.getAllAdmissions();
    }

    @GetMapping("/{id}")
    public AdmissionDTO getAdmissionById(
            @PathVariable Long id) {
        return admissionService.getAdmissionById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<AdmissionDTO> getAdmissionsByPatient(
            @PathVariable Long patientId) {
        return admissionService.getAdmissionsByPatient(patientId);
    }

    @GetMapping("/room/{roomId}")
    public List<AdmissionDTO> getAdmissionsByRoom(
            @PathVariable Long roomId) {
        return admissionService.getAdmissionsByRoom(roomId);
    }

    @PutMapping("/{id}")
    public AdmissionDTO updateAdmission(
            @PathVariable Long id,
            @Valid @RequestBody AdmissionDTO dto) {
        return admissionService.updateAdmission(id, dto);
    }

    @PutMapping("/{id}/discharge")
    public AdmissionDTO dischargePatient(
            @PathVariable Long id) {
        return admissionService.dischargePatient(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmission(@PathVariable Long id) {
        admissionService.deleteAdmission(id);
    }
}