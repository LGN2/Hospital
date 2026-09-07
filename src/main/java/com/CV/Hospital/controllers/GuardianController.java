package com.CV.Hospital.controllers;

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
    public Guardian addGuardian(
            @RequestBody Guardian guardian,
            @RequestParam Long patientId) {

        return guardianService.addGuardian(guardian, patientId);
    }

    @GetMapping
    public List<Guardian> getAllGuardians() {
        return guardianService.getAllGuardians();
    }
}
