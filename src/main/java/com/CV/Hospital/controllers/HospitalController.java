package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.HospitalDTO;
import com.CV.Hospital.services.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public HospitalDTO addHospital(
            @Valid @RequestBody HospitalDTO dto) {
        return hospitalService.addHospital(dto);
    }

    @GetMapping
    public List<HospitalDTO> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/{id}")
    public HospitalDTO getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id);
    }

    @PutMapping("/{id}")
    public HospitalDTO updateHospital(
            @PathVariable Long id,
            @Valid @RequestBody HospitalDTO dto) {
        return hospitalService.updateHospital(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
    }
}