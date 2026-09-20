package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.DepartmentDTO;
import com.CV.Hospital.entities.Department;
import com.CV.Hospital.services.DepartmentService;
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
            @RequestBody Department department,
            @RequestParam Long hospitalId) {

        return departmentService.convertToDTO(
                departmentService.addDepartment(department, hospitalId)
        );
    }

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.convertToDTO(
                departmentService.getAllDepartments()
        );
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable Long id) {
        return departmentService.convertToDTO(
                departmentService.getDepartmentById(id)
        );
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<DepartmentDTO> getDepartmentsByHospital(
            @PathVariable Long hospitalId) {

        return departmentService.convertToDTO(
                departmentService.getDepartmentsByHospital(hospitalId)
        );
    }

    @PutMapping("/{id}")
    public DepartmentDTO updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {

        return departmentService.convertToDTO(
                departmentService.updateDepartment(id, department)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}