package com.CV.Hospital.services;

import com.CV.Hospital.dto.HospitalDTO;
import com.CV.Hospital.entities.Hospital;
import com.CV.Hospital.repositories.DepartmentRepository;
import com.CV.Hospital.repositories.DoctorRepository;
import com.CV.Hospital.repositories.HospitalRepository;
import com.CV.Hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public HospitalDTO addHospital(HospitalDTO dto) {
        Hospital hospital = new Hospital();
        hospital.setName(dto.getName());
        hospital.setLocation(dto.getLocation());

        return convertToDTO(hospitalRepository.save(hospital));
    }

    public List<HospitalDTO> getAllHospitals() {
        return convertToDTO(hospitalRepository.findByIsActiveTrue());
    }

    public HospitalDTO getHospitalById(Long id) {
        return convertToDTO(findActiveHospital(id));
    }

    public HospitalDTO updateHospital(Long id, HospitalDTO dto) {
        Hospital hospital = findActiveHospital(id);

        hospital.setName(dto.getName());
        hospital.setLocation(dto.getLocation());

        return convertToDTO(hospitalRepository.save(hospital));
    }

    public void deleteHospital(Long id) {
        Hospital hospital = findActiveHospital(id);
        hospital.setIsActive(false);
        hospitalRepository.save(hospital);
    }

    private Hospital findActiveHospital(Long id) {
        return hospitalRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found"));
    }

    public HospitalDTO convertToDTO(Hospital hospital) {
        return HospitalDTO.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .location(hospital.getLocation())
                .build();
    }

    public List<HospitalDTO> convertToDTO(List<Hospital> hospitals) {
        return hospitals.stream()
                .map(this::convertToDTO)
                .toList();
    }
}