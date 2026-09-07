package com.CV.Hospital.services;

import com.CV.Hospital.entities.Guardian;
import com.CV.Hospital.entities.Patient;
import com.CV.Hospital.repositories.GuardianRepository;
import com.CV.Hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuardianService {

    private final GuardianRepository guardianRepository;
    private final PatientRepository patientRepository;

    public Guardian addGuardian(
            Guardian guardian,
            Long patientId) {
        Patient patient =
                patientRepository.findByIdAndIsActiveTrue(patientId)
                        .orElseThrow(() ->
                                new RuntimeException("Patient not found"));
        guardian.setPatient(patient);
        guardian.setIsActive(true);
        return guardianRepository.save(guardian);
    }

    public List<Guardian> getAllGuardians() {
        return guardianRepository.findByIsActiveTrue();
    }

    public Guardian getGuardianById(Long id) {
        return guardianRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Guardian not found"));
    }

}
