package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.AdmissionDTO;
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
    public AdmissionDTO admitPatient(
            @RequestBody Admission admission,
            @RequestParam Long patientId,
            @RequestParam Long roomId) {

        return admissionService.convertToDTO(
                admissionService.admitPatient(
                        admission,
                        patientId,
                        roomId
                )
        );
    }

    @GetMapping
    public List<AdmissionDTO> getAllAdmissions() {
        return admissionService.convertToDTO(
                admissionService.getAllAdmissions()
        );
    }

    @GetMapping("/{id}")
    public AdmissionDTO getAdmissionById(@PathVariable Long id) {
        return admissionService.convertToDTO(
                admissionService.getAdmissionById(id)
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<AdmissionDTO> getAdmissionsByPatient(
            @PathVariable Long patientId) {

        return admissionService.convertToDTO(
                admissionService.getAdmissionsByPatient(patientId)
        );
    }

    @GetMapping("/room/{roomId}")
    public List<AdmissionDTO> getAdmissionsByRoom(
            @PathVariable Long roomId) {

        return admissionService.convertToDTO(
                admissionService.getAdmissionsByRoom(roomId)
        );
    }

    @PutMapping("/{id}")
    public AdmissionDTO updateAdmission(
            @PathVariable Long id,
            @RequestBody Admission admission) {

        return admissionService.convertToDTO(
                admissionService.updateAdmission(id, admission)
        );
    }

    @PutMapping("/{id}/discharge")
    public AdmissionDTO dischargePatient(@PathVariable Long id) {
        return admissionService.convertToDTO(
                admissionService.dischargePatient(id)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteAdmission(@PathVariable Long id) {
        admissionService.deleteAdmission(id);
    }
}