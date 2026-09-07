package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Department;
import com.CV.Hospital.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
