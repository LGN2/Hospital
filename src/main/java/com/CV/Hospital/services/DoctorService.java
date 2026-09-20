package com.CV.Hospital.services;

import com.CV.Hospital.dto.DoctorDTO;
import com.CV.Hospital.entities.Department;
import com.CV.Hospital.entities.Doctor;
import com.CV.Hospital.entities.Hospital;
import com.CV.Hospital.repositories.DepartmentRepository;
import com.CV.Hospital.repositories.DoctorRepository;
import com.CV.Hospital.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    public DoctorDTO addDoctor(DoctorDTO dto) {

        Department department = getDepartment(dto.getDepartmentId());
        Hospital hospital = getHospital(dto.getHospitalId());

        validateDepartmentHospital(department, hospital);

        Doctor doctor = new Doctor();

        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setPhoneNumber(dto.getPhoneNumber());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setDepartment(department);
        doctor.setHospital(hospital);

        return convertToDTO(doctorRepository.save(doctor));
    }

    public List<DoctorDTO> getAllDoctors() {
        return convertToDTO(
                doctorRepository.findByIsActiveTrue()
        );
    }

    public DoctorDTO getDoctorById(Long id) {
        return convertToDTO(findActiveDoctor(id));
    }

    public List<DoctorDTO> getDoctorsByDepartment(
            Long departmentId) {

        return convertToDTO(
                doctorRepository
                        .findByDepartmentIdAndIsActiveTrue(departmentId)
        );
    }

    public List<DoctorDTO> getDoctorsByHospital(Long hospitalId) {
        return convertToDTO(
                doctorRepository
                        .findByHospitalIdAndIsActiveTrue(hospitalId)
        );
    }

    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {

        Doctor doctor = findActiveDoctor(id);

        Department department = getDepartment(dto.getDepartmentId());
        Hospital hospital = getHospital(dto.getHospitalId());

        validateDepartmentHospital(department, hospital);

        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setPhoneNumber(dto.getPhoneNumber());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setDepartment(department);
        doctor.setHospital(hospital);

        return convertToDTO(doctorRepository.save(doctor));
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = findActiveDoctor(id);
        doctor.setIsActive(false);
        doctorRepository.save(doctor);
    }

    private Doctor findActiveDoctor(Long id) {
        return doctorRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found"));
    }

    private Department getDepartment(Long id) {
        return departmentRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));
    }

    private Hospital getHospital(Long id) {
        return hospitalRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found"));
    }

    private void validateDepartmentHospital(
            Department department,
            Hospital hospital) {

        if (!department.getHospital().getId()
                .equals(hospital.getId())) {

            throw new IllegalArgumentException(
                    "Department does not belong to selected hospital"
            );
        }
    }

    public DoctorDTO convertToDTO(Doctor doctor) {
        return DoctorDTO.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .email(doctor.getEmail())
                .phoneNumber(doctor.getPhoneNumber())
                .specialization(doctor.getSpecialization())
                .departmentId(doctor.getDepartment().getId())
                .hospitalId(doctor.getHospital().getId())
                .build();
    }

    public List<DoctorDTO> convertToDTO(List<Doctor> doctors) {
        return doctors.stream()
                .map(this::convertToDTO)
                .toList();
    }
}