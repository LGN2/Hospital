package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Bill;
import com.CV.Hospital.entities.type.BillStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository
        extends JpaRepository<Bill, Long> {
    List<Bill> findByIsActiveTrue();
    Optional<Bill> findByIdAndIsActiveTrue(Long id);
    List<Bill> findByPatientIdAndIsActiveTrue(Long patientId);
    List<Bill> findByStatusAndIsActiveTrue(BillStatusType status);
    List<Bill> findByPatientIdAndStatusAndIsActiveTrue(
            Long patientId,
            BillStatusType status
    );
}