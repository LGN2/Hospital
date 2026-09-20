package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.DepartmentDTO;
import com.CV.Hospital.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public DepartmentDTO addDepartment(
            @Valid @RequestBody DepartmentDTO dto) {
        return departmentService.addDepartment(dto);
    }

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentById(
            @PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<DepartmentDTO> getDepartmentsByHospital(
            @PathVariable Long hospitalId) {
        return departmentService
                .getDepartmentsByHospital(hospitalId);
    }

    @PutMapping("/{id}")
    public DepartmentDTO updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentDTO dto) {
        return departmentService.updateDepartment(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}