package com.CV.Hospital.services;

import com.CV.Hospital.entities.Hospital;
import com.CV.Hospital.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    //Create
    public Hospital addHospital(Hospital hospital) {
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital cannot be null");
        }
        if (hospital.getName() == null || hospital.getName().isBlank()) {
            throw new IllegalArgumentException("Hospital name is required");
        }
        if (hospital.getLocation() == null || hospital.getLocation().isBlank()) {
            throw new IllegalArgumentException("Hospital location is required");
        }
        hospital.setIsActive(true);
        return hospitalRepository.save(hospital);
    }


    //Get All
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findByIsActiveTrue();
    }


    //Get By ID
    public Hospital getHospitalById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Hospital ID cannot be null");
        }
        return hospitalRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found with ID: " + id));
    }


    //Update
    public Hospital updateHospital(Long id, Hospital updatedHospital) {
        if (updatedHospital == null) {
            throw new IllegalArgumentException("Hospital cannot be null");
        }
        Hospital existingHospital = getHospitalById(id);
        if (updatedHospital.getName() != null
                && !updatedHospital.getName().isBlank()) {
            existingHospital.setName(updatedHospital.getName());
        }
        if (updatedHospital.getLocation() != null
                && !updatedHospital.getLocation().isBlank()) {
            existingHospital.setLocation(updatedHospital.getLocation());
        }
        return hospitalRepository.save(existingHospital);
    }

    //Delete
    public void deleteHospital(Long id) {
        Hospital hospital = getHospitalById(id);
        hospital.setIsActive(false);
        hospitalRepository.save(hospital);
    }
}
