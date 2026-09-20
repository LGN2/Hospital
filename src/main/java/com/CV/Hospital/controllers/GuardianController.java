package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.GuardianDTO;
import com.CV.Hospital.services.GuardianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")
@RequiredArgsConstructor
public class GuardianController {

    private final GuardianService guardianService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuardianDTO addGuardian(
            @Valid @RequestBody GuardianDTO dto) {
        return guardianService.addGuardian(dto);
    }

    @GetMapping
    public List<GuardianDTO> getAllGuardians() {
        return guardianService.getAllGuardians();
    }

    @GetMapping("/{id}")
    public GuardianDTO getGuardianById(
            @PathVariable Long id) {
        return guardianService.getGuardianById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<GuardianDTO> getGuardiansByPatient(
            @PathVariable Long patientId) {
        return guardianService.getGuardiansByPatient(patientId);
    }

    @PutMapping("/{id}")
    public GuardianDTO updateGuardian(
            @PathVariable Long id,
            @Valid @RequestBody GuardianDTO dto) {
        return guardianService.updateGuardian(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuardian(@PathVariable Long id) {
        guardianService.deleteGuardian(id);
    }
}