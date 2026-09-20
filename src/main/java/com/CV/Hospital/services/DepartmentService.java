package com.CV.Hospital.services;

import com.CV.Hospital.dto.DepartmentDTO;
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

    public DepartmentDTO addDepartment(DepartmentDTO dto) {

        Hospital hospital = hospitalRepository
                .findByIdAndIsActiveTrue(dto.getHospitalId())
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found"));

        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department.setHospital(hospital);

        return convertToDTO(departmentRepository.save(department));
    }

    public List<DepartmentDTO> getAllDepartments() {
        return convertToDTO(
                departmentRepository.findByIsActiveTrue()
        );
    }

    public DepartmentDTO getDepartmentById(Long id) {
        return convertToDTO(findActiveDepartment(id));
    }

    public List<DepartmentDTO> getDepartmentsByHospital(
            Long hospitalId) {

        return convertToDTO(
                departmentRepository
                        .findByHospitalIdAndIsActiveTrue(hospitalId)
        );
    }

    public DepartmentDTO updateDepartment(
            Long id,
            DepartmentDTO dto) {

        Department department = findActiveDepartment(id);

        department.setName(dto.getName());
        department.setDescription(dto.getDescription());

        if (dto.getHospitalId() != null) {
            Hospital hospital = hospitalRepository
                    .findByIdAndIsActiveTrue(dto.getHospitalId())
                    .orElseThrow(() ->
                            new RuntimeException("Hospital not found"));

            department.setHospital(hospital);
        }

        return convertToDTO(
                departmentRepository.save(department)
        );
    }

    public void deleteDepartment(Long id) {
        Department department = findActiveDepartment(id);
        department.setIsActive(false);
        departmentRepository.save(department);
    }

    private Department findActiveDepartment(Long id) {
        return departmentRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));
    }

    public DepartmentDTO convertToDTO(Department department) {
        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .hospitalId(department.getHospital().getId())
                .build();
    }

    public List<DepartmentDTO> convertToDTO(
            List<Department> departments) {

        return departments.stream()
                .map(this::convertToDTO)
                .toList();
    }
}