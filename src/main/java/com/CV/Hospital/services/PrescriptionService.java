package com.CV.Hospital.services;

import com.CV.Hospital.repositories.MedicalRecordRepository;
import com.CV.Hospital.repositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicalRecordRepository medicalRecordRepository;
}
