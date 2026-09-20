package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.StaffDTO;
import com.CV.Hospital.services.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StaffDTO addStaff(
            @Valid @RequestBody StaffDTO dto) {
        return staffService.addStaff(dto);
    }

    @GetMapping
    public List<StaffDTO> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public StaffDTO getStaffById(@PathVariable Long id) {
        return staffService.getStaffById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<StaffDTO> getStaffByDepartment(
            @PathVariable Long departmentId) {
        return staffService.getStaffByDepartment(departmentId);
    }

    @PutMapping("/{id}")
    public StaffDTO updateStaff(
            @PathVariable Long id,
            @Valid @RequestBody StaffDTO dto) {
        return staffService.updateStaff(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
    }
}