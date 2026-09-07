package com.CV.Hospital.services;

import com.CV.Hospital.repositories.DepartmentRepository;
import com.CV.Hospital.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final DepartmentRepository departmentRepository;
}
