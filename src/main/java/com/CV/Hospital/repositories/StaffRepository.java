package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository
        extends JpaRepository<Staff, Long> {
    List<Staff> findByIsActiveTrue();
    Optional<Staff> findByIdAndIsActiveTrue(Long id);
    List<Staff> findByDepartmentIdAndIsActiveTrue(Long departmentId);
}