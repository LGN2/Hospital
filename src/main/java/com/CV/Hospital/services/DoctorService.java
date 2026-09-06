package com.CV.Hospital.services;

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

    public Doctor addDoctor(
            Doctor doctor,
            Long departmentId,
            Long hospitalId) {
        Department department =
                departmentRepository.findByIdAndIsActiveTrue(departmentId)
                        .orElseThrow(() ->
                                new RuntimeException("Department not found"));
        Hospital hospital =
                hospitalRepository.findByIdAndIsActiveTrue(hospitalId)
                        .orElseThrow(() ->
                                new RuntimeException("Hospital not found"));
        if (!department.getHospital().getId().equals(hospital.getId())) {
            throw new IllegalArgumentException(
                    "Department does not belong to the selected hospital"
            );
        }
        doctor.setDepartment(department);
        doctor.setHospital(hospital);
        doctor.setIsActive(true);
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findByIsActiveTrue();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found with ID: " + id));
    }
}
