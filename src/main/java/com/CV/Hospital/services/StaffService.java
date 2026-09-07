package com.CV.Hospital.services;

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

    public Staff addStaff(
            Staff staff,
            Long departmentId) {
        Department department =
                departmentRepository.findByIdAndIsActiveTrue(departmentId)
                        .orElseThrow(() ->
                                new RuntimeException("Department not found"));
        staff.setDepartment(department);
        staff.setIsActive(true);
        return staffRepository.save(staff);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findByIsActiveTrue();
    }

    public Staff getStaffById(Long id) {
        return staffRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Staff not found"));
    }
}
