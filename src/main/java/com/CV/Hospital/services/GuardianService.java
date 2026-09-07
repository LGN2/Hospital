package com.CV.Hospital.services;

import com.CV.Hospital.repositories.GuardianRepository;
import com.CV.Hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuardianService {

    private final GuardianRepository guardianRepository;
    private final PatientRepository patientRepository;
}
