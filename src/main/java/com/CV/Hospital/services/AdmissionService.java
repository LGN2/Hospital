package com.CV.Hospital.services;

import com.CV.Hospital.repositories.AdmissionRepository;
import com.CV.Hospital.repositories.PatientRepository;
import com.CV.Hospital.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmissionService {
    private final AdmissionRepository admissionRepository;
    private final PatientRepository patientRepository;
    private final RoomRepository roomRepository;
}
