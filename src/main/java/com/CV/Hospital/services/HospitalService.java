package com.CV.Hospital.services;

import com.CV.Hospital.entities.Hospital;
import com.CV.Hospital.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
