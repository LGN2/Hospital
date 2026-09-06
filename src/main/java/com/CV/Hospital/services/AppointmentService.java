package com.CV.Hospital.services;

import com.CV.Hospital.repositories.AppointmentRepository;
import com.CV.Hospital.repositories.DoctorRepository;
import com.CV.Hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

}
