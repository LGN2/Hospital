package com.CV.Hospital.services;

import com.CV.Hospital.entities.Department;
import com.CV.Hospital.entities.Hospital;
import com.CV.Hospital.repositories.DepartmentRepository;
import com.CV.Hospital.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;


    public Department addDepartment(
            Department department,
            Long hospitalId) {
        Hospital hospital = hospitalRepository.findByIdAndIsActiveTrue(hospitalId)
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found with ID: " + hospitalId));
        department.setHospital(hospital);
        department.setIsActive(true);
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findByIsActiveTrue();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with ID: " + id));
    }

    public List<Department> getDepartmentsByHospital(Long hospitalId) {
        return departmentRepository.findByHospitalIdAndIsActiveTrue(hospitalId);
    }

    public Department updateDepartment(
            Long id,
            Department updatedDepartment) {
        Department department = getDepartmentById(id);
        if (updatedDepartment.getName() != null &&
                !updatedDepartment.getName().isBlank()) {
            department.setName(updatedDepartment.getName());
        }
        if (updatedDepartment.getDescription() != null) {
            department.setDescription(updatedDepartment.getDescription());
        }
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        department.setIsActive(false);
        departmentRepository.save(department);
    }
}
