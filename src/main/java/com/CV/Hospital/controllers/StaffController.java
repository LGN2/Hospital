package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.StaffDTO;
import com.CV.Hospital.entities.Staff;
import com.CV.Hospital.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public StaffDTO addStaff(
            @RequestBody Staff staff,
            @RequestParam Long departmentId) {

        return staffService.convertToDTO(
                staffService.addStaff(staff, departmentId)
        );
    }

    @GetMapping
    public List<StaffDTO> getAllStaff() {
        return staffService.convertToDTO(
                staffService.getAllStaff()
        );
    }

    @GetMapping("/{id}")
    public StaffDTO getStaffById(@PathVariable Long id) {
        return staffService.convertToDTO(
                staffService.getStaffById(id)
        );
    }

    @GetMapping("/department/{departmentId}")
    public List<StaffDTO> getStaffByDepartment(
            @PathVariable Long departmentId) {

        return staffService.convertToDTO(
                staffService.getStaffByDepartment(departmentId)
        );
    }

    @PutMapping("/{id}")
    public StaffDTO updateStaff(
            @PathVariable Long id,
            @RequestBody Staff staff) {

        return staffService.convertToDTO(
                staffService.updateStaff(id, staff)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
    }
}