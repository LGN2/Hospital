package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Staff;
import com.CV.Hospital.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public Staff addStaff(
            @RequestBody Staff staff,
            @RequestParam Long departmentId) {

        return staffService.addStaff(staff, departmentId);
    }
}
