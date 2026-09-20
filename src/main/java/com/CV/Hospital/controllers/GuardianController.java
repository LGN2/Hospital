package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.GuardianDTO;
import com.CV.Hospital.entities.Guardian;
import com.CV.Hospital.services.GuardianService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")
@RequiredArgsConstructor
public class GuardianController {

    private final GuardianService guardianService;

    @PostMapping
    public GuardianDTO addGuardian(
            @RequestBody Guardian guardian,
            @RequestParam Long patientId) {

        return guardianService.convertToDTO(
                guardianService.addGuardian(guardian, patientId)
        );
    }

    @GetMapping
    public List<GuardianDTO> getAllGuardians() {
        return guardianService.convertToDTO(
                guardianService.getAllGuardians()
        );
    }

    @GetMapping("/{id}")
    public GuardianDTO getGuardianById(@PathVariable Long id) {
        return guardianService.convertToDTO(
                guardianService.getGuardianById(id)
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<GuardianDTO> getGuardiansByPatient(
            @PathVariable Long patientId) {

        return guardianService.convertToDTO(
                guardianService.getGuardiansByPatient(patientId)
        );
    }

    @PutMapping("/{id}")
    public GuardianDTO updateGuardian(
            @PathVariable Long id,
            @RequestBody Guardian guardian) {

        return guardianService.convertToDTO(
                guardianService.updateGuardian(id, guardian)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteGuardian(@PathVariable Long id) {
        guardianService.deleteGuardian(id);
    }
}