package com.CV.Hospital.services;

import com.CV.Hospital.repositories.BillRepository;
import com.CV.Hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final PatientRepository patientRepository;
}
