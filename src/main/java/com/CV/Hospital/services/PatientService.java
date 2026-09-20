package com.CV.Hospital.services;

import com.CV.Hospital.dto.PatientDTO;
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

    public PatientDTO addPatient(PatientDTO dto) {

        Hospital hospital = hospitalRepository
                .findByIdAndIsActiveTrue(dto.getHospitalId())
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found"));

        Patient patient = new Patient();

        patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setHospital(hospital);

        return convertToDTO(patientRepository.save(patient));
    }

    public List<PatientDTO> getAllPatients() {
        return convertToDTO(
                patientRepository.findByIsActiveTrue()
        );
    }

    public PatientDTO getPatientById(Long id) {
        return convertToDTO(findActivePatient(id));
    }

    public List<PatientDTO> getPatientsByHospital(Long hospitalId) {
        return convertToDTO(
                patientRepository
                        .findByHospitalIdAndIsActiveTrue(hospitalId)
        );
    }

    public PatientDTO updatePatient(Long id, PatientDTO dto) {

        Patient patient = findActivePatient(id);

        Hospital hospital = hospitalRepository
                .findByIdAndIsActiveTrue(dto.getHospitalId())
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found"));

        patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setHospital(hospital);

        return convertToDTO(patientRepository.save(patient));
    }

    public void deletePatient(Long id) {
        Patient patient = findActivePatient(id);
        patient.setIsActive(false);
        patientRepository.save(patient);
    }

    private Patient findActivePatient(Long id) {
        return patientRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));
    }

    public PatientDTO convertToDTO(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .gender(patient.getGender())
                .phoneNumber(patient.getPhoneNumber())
                .bloodGroup(patient.getBloodGroup())
                .hospitalId(patient.getHospital().getId())
                .build();
    }

    public List<PatientDTO> convertToDTO(List<Patient> patients) {
        return patients.stream()
                .map(this::convertToDTO)
                .toList();
    }
}