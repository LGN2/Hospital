package com.CV.Hospital.services;

import com.CV.Hospital.entities.Bill;
import com.CV.Hospital.entities.Patient;
import com.CV.Hospital.entities.type.BillStatusType;
import com.CV.Hospital.repositories.BillRepository;
import com.CV.Hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final PatientRepository patientRepository;

    public Bill addBill(Bill bill, Long patientId) {
        Patient patient =
                patientRepository.findByIdAndIsActiveTrue(patientId)
                        .orElseThrow(() ->
                                new RuntimeException("Patient not found"));
        if (bill.getAmount() == null ||
                bill.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Bill amount must be greater than zero"
            );
        }
        bill.setPatient(patient);
        bill.setIsActive(true);
        if (bill.getBillDate() == null) {
            bill.setBillDate(LocalDate.now());
        }
        if (bill.getStatus() == null) {
            bill.setStatus(BillStatusType.UNPAID);
        }
        return billRepository.save(bill);
    }

    public List<Bill> getAllBills() {
        return billRepository.findByIsActiveTrue();
    }

    public Bill getBillById(Long id) {
        return billRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Bill not found"));
    }

    public List<Bill> getBillsByPatient(Long patientId) {
        return billRepository
                .findByPatientIdAndIsActiveTrue(patientId);
    }
}
