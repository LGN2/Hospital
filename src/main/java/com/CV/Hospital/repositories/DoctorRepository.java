package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository
        extends JpaRepository<Doctor, Long> {
    List<Doctor> findByIsActiveTrue();
    Optional<Doctor> findByIdAndIsActiveTrue(Long id);
    List<Doctor> findByDepartmentIdAndIsActiveTrue(Long departmentId);
    List<Doctor> findByHospitalIdAndIsActiveTrue(Long hospitalId);
}
