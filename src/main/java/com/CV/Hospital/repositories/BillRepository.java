package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Bill;
import com.CV.Hospital.entities.type.BillStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository
        extends JpaRepository<Bill, Long> {

    List<Bill> findByIsActiveTrue();

    Optional<Bill> findByIdAndIsActiveTrue(Long id);

    List<Bill> findByPatientIdAndIsActiveTrue(
            Long patientId
    );

    List<Bill> findByStatusAndIsActiveTrue(
            BillStatusType status
    );

    List<Bill> findByPatientIdAndStatusAndIsActiveTrue(
            Long patientId,
            BillStatusType status
    );

    @Query("""
            SELECT b
            FROM Bill b
            WHERE b.status = :status
            AND b.isActive = true
            AND b.patient.isActive = true
            """)
    List<Bill> findOutstandingBills(
            @Param("status") BillStatusType status
    );

    @Query("""
            SELECT COALESCE(SUM(b.amount), 0)
            FROM Bill b
            WHERE b.patient.id = :patientId
            AND b.isActive = true
            """)
    BigDecimal getTotalBilledAmountForPatient(
            @Param("patientId") Long patientId
    );
}