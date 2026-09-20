package com.CV.Hospital.services;

import com.CV.Hospital.dto.BillDTO;
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

    public BillDTO addBill(BillDTO dto) {

        Patient patient = getPatient(dto.getPatientId());

        Bill bill = new Bill();

        bill.setAmount(dto.getAmount());
        bill.setStatus(
                dto.getStatus() == null
                        ? BillStatusType.UNPAID
                        : dto.getStatus()
        );
        bill.setBillDate(
                dto.getBillDate() == null
                        ? LocalDate.now()
                        : dto.getBillDate()
        );
        bill.setPatient(patient);

        return convertToDTO(billRepository.save(bill));
    }

    public List<BillDTO> getAllBills() {
        return convertToDTO(billRepository.findByIsActiveTrue());
    }

    public BillDTO getBillById(Long id) {
        return convertToDTO(findActiveBill(id));
    }

    public List<BillDTO> getBillsByPatient(Long patientId) {
        return convertToDTO(
                billRepository
                        .findByPatientIdAndIsActiveTrue(patientId)
        );
    }

    public List<BillDTO> getUnpaidBills() {
        return convertToDTO(
                billRepository.findByStatusAndIsActiveTrue(
                        BillStatusType.UNPAID
                )
        );
    }

    public List<BillDTO> getUnpaidBillsByPatient(
            Long patientId) {

        return convertToDTO(
                billRepository
                        .findByPatientIdAndStatusAndIsActiveTrue(
                                patientId,
                                BillStatusType.UNPAID
                        )
        );
    }

    public BillDTO updateBill(Long id, BillDTO dto) {

        Bill bill = findActiveBill(id);
        Patient patient = getPatient(dto.getPatientId());

        bill.setAmount(dto.getAmount());
        bill.setStatus(dto.getStatus());
        bill.setBillDate(dto.getBillDate());
        bill.setPatient(patient);

        return convertToDTO(billRepository.save(bill));
    }

    public void deleteBill(Long id) {
        Bill bill = findActiveBill(id);
        bill.setIsActive(false);
        billRepository.save(bill);
    }

    private Bill findActiveBill(Long id) {
        return billRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Bill not found"));
    }

    private Patient getPatient(Long id) {
        return patientRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));
    }

    public BillDTO convertToDTO(Bill bill) {
        return BillDTO.builder()
                .id(bill.getId())
                .amount(bill.getAmount())
                .status(bill.getStatus())
                .billDate(bill.getBillDate())
                .patientId(bill.getPatient().getId())
                .build();
    }

    public List<BillDTO> convertToDTO(List<Bill> bills) {
        return bills.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<BillDTO> getOutstandingBills() {

        return convertToDTO(
                billRepository.findOutstandingBills(
                        BillStatusType.UNPAID
                )
        );
    }

    public BigDecimal getTotalBilledAmountForPatient(
            Long patientId) {

        patientRepository.findByIdAndIsActiveTrue(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        return billRepository
                .getTotalBilledAmountForPatient(patientId);
    }
}