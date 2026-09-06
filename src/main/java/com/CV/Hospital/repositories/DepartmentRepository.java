package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
    List<Department> findByIsActiveTrue();
    Optional<Department> findByIdAndIsActiveTrue(Long id);
    List<Department> findByHospitalIdAndIsActiveTrue(Long hospitalId);
}
