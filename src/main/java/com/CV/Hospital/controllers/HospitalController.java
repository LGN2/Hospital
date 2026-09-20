package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.HospitalDTO;
import com.CV.Hospital.entities.Hospital;
import com.CV.Hospital.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public HospitalDTO addHospital(@RequestBody Hospital hospital) {
        return hospitalService.convertToDTO(
                hospitalService.addHospital(hospital)
        );
    }

    @GetMapping
    public List<HospitalDTO> getAllHospitals() {
        return hospitalService.convertToDTO(
                hospitalService.getAllHospitals()
        );
    }

    @GetMapping("/{id}")
    public HospitalDTO getHospitalById(@PathVariable Long id) {
        return hospitalService.convertToDTO(
                hospitalService.getHospitalById(id)
        );
    }

    @PutMapping("/{id}")
    public HospitalDTO updateHospital(
            @PathVariable Long id,
            @RequestBody Hospital hospital) {

        return hospitalService.convertToDTO(
                hospitalService.updateHospital(id, hospital)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
    }
}