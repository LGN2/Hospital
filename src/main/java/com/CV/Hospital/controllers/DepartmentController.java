package com.CV.Hospital.controllers;

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
    public Department addDepartment(
            @RequestBody Department department,
            @RequestParam Long hospitalId) {

        return departmentService.addDepartment(department, hospitalId);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<Department> getDepartmentsByHospital(
            @PathVariable Long hospitalId) {

        return departmentService.getDepartmentsByHospital(hospitalId);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {

        return departmentService.updateDepartment(id, department);
    }

}
