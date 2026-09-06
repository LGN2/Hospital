package com.CV.Hospital.services;

import com.CV.Hospital.entities.Hospital;
import com.CV.Hospital.entities.Patient;
import com.CV.Hospital.repositories.HospitalRepository;
import com.CV.Hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    public Patient addPatient(Patient patient, Long hospitalId) {
        Hospital hospital =
                hospitalRepository.findByIdAndIsActiveTrue(hospitalId)
                        .orElseThrow(() ->
                                new RuntimeException("Hospital not found"));
        patient.setHospital(hospital);
        patient.setIsActive(true);
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findByIsActiveTrue();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found with ID: " + id));
    }

    public List<Patient> getPatientsByHospital(Long hospitalId) {
        return patientRepository
                .findByHospitalIdAndIsActiveTrue(hospitalId);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient patient = getPatientById(id);
        if (updatedPatient.getName() != null) {
            patient.setName(updatedPatient.getName());
        }
        if (updatedPatient.getGender() != null) {
            patient.setGender(updatedPatient.getGender());
        }
        if (updatedPatient.getPhoneNumber() != null) {
            patient.setPhoneNumber(updatedPatient.getPhoneNumber());
        }
        if (updatedPatient.getBloodGroup() != null) {
            patient.setBloodGroup(updatedPatient.getBloodGroup());
        }
        return patientRepository.save(patient);
    }
}
