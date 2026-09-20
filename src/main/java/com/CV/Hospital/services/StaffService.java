package com.CV.Hospital.services;

import com.CV.Hospital.dto.StaffDTO;
import com.CV.Hospital.entities.Department;
import com.CV.Hospital.entities.Staff;
import com.CV.Hospital.repositories.DepartmentRepository;
import com.CV.Hospital.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final DepartmentRepository departmentRepository;

    public StaffDTO addStaff(StaffDTO dto) {

        Department department =
                getDepartment(dto.getDepartmentId());

        Staff staff = new Staff();

        staff.setName(dto.getName());
        staff.setRole(dto.getRole());
        staff.setPhoneNumber(dto.getPhoneNumber());
        staff.setDepartment(department);

        return convertToDTO(staffRepository.save(staff));
    }

    public List<StaffDTO> getAllStaff() {
        return convertToDTO(
                staffRepository.findByIsActiveTrue()
        );
    }

    public StaffDTO getStaffById(Long id) {
        return convertToDTO(findActiveStaff(id));
    }

    public List<StaffDTO> getStaffByDepartment(
            Long departmentId) {

        return convertToDTO(
                staffRepository
                        .findByDepartmentIdAndIsActiveTrue(departmentId)
        );
    }

    public StaffDTO updateStaff(Long id, StaffDTO dto) {

        Staff staff = findActiveStaff(id);

        Department department =
                getDepartment(dto.getDepartmentId());

        staff.setName(dto.getName());
        staff.setRole(dto.getRole());
        staff.setPhoneNumber(dto.getPhoneNumber());
        staff.setDepartment(department);

        return convertToDTO(staffRepository.save(staff));
    }

    public void deleteStaff(Long id) {
        Staff staff = findActiveStaff(id);
        staff.setIsActive(false);
        staffRepository.save(staff);
    }

    private Staff findActiveStaff(Long id) {
        return staffRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Staff not found"));
    }

    private Department getDepartment(Long id) {
        return departmentRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));
    }

    public StaffDTO convertToDTO(Staff staff) {
        return StaffDTO.builder()
                .id(staff.getId())
                .name(staff.getName())
                .role(staff.getRole())
                .phoneNumber(staff.getPhoneNumber())
                .departmentId(staff.getDepartment().getId())
                .build();
    }

    public List<StaffDTO> convertToDTO(List<Staff> staff) {
        return staff.stream()
                .map(this::convertToDTO)
                .toList();
    }
}