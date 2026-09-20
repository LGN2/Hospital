package com.CV.Hospital.services;

import com.CV.Hospital.dto.GuardianDTO;
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

    public GuardianDTO addGuardian(GuardianDTO dto) {

        Patient patient = getPatient(dto.getPatientId());

        Guardian guardian = new Guardian();

        guardian.setName(dto.getName());
        guardian.setRelationship(dto.getRelationship());
        guardian.setPhoneNumber(dto.getPhoneNumber());
        guardian.setPatient(patient);

        return convertToDTO(
                guardianRepository.save(guardian)
        );
    }

    public List<GuardianDTO> getAllGuardians() {
        return convertToDTO(
                guardianRepository.findByIsActiveTrue()
        );
    }

    public GuardianDTO getGuardianById(Long id) {
        return convertToDTO(findActiveGuardian(id));
    }

    public List<GuardianDTO> getGuardiansByPatient(
            Long patientId) {

        return convertToDTO(
                guardianRepository
                        .findByPatientIdAndIsActiveTrue(patientId)
        );
    }

    public GuardianDTO updateGuardian(
            Long id,
            GuardianDTO dto) {

        Guardian guardian = findActiveGuardian(id);
        Patient patient = getPatient(dto.getPatientId());

        guardian.setName(dto.getName());
        guardian.setRelationship(dto.getRelationship());
        guardian.setPhoneNumber(dto.getPhoneNumber());
        guardian.setPatient(patient);

        return convertToDTO(
                guardianRepository.save(guardian)
        );
    }

    public void deleteGuardian(Long id) {
        Guardian guardian = findActiveGuardian(id);
        guardian.setIsActive(false);
        guardianRepository.save(guardian);
    }

    private Guardian findActiveGuardian(Long id) {
        return guardianRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Guardian not found"));
    }

    private Patient getPatient(Long id) {
        return patientRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));
    }

    public GuardianDTO convertToDTO(Guardian guardian) {
        return GuardianDTO.builder()
                .id(guardian.getId())
                .name(guardian.getName())
                .relationship(guardian.getRelationship())
                .phoneNumber(guardian.getPhoneNumber())
                .patientId(guardian.getPatient().getId())
                .build();
    }

    public List<GuardianDTO> convertToDTO(
            List<Guardian> guardians) {

        return guardians.stream()
                .map(this::convertToDTO)
                .toList();
    }
}