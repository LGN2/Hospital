package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.BillDTO;
import com.CV.Hospital.services.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BillDTO addBill(
            @Valid @RequestBody BillDTO dto) {
        return billService.addBill(dto);
    }

    @GetMapping
    public List<BillDTO> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public BillDTO getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<BillDTO> getBillsByPatient(
            @PathVariable Long patientId) {
        return billService.getBillsByPatient(patientId);
    }

    @GetMapping("/unpaid")
    public List<BillDTO> getUnpaidBills() {
        return billService.getUnpaidBills();
    }

    @GetMapping("/patient/{patientId}/unpaid")
    public List<BillDTO> getUnpaidBillsByPatient(
            @PathVariable Long patientId) {
        return billService.getUnpaidBillsByPatient(patientId);
    }

    @PutMapping("/{id}")
    public BillDTO updateBill(
            @PathVariable Long id,
            @Valid @RequestBody BillDTO dto) {
        return billService.updateBill(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
    }

    @GetMapping("/outstanding")
    public List<BillDTO> getOutstandingBills() {

        return billService.getOutstandingBills();
    }

    @GetMapping("/patient/{patientId}/total")
    public BigDecimal getTotalBilledAmountForPatient(
            @PathVariable Long patientId) {

        return billService
                .getTotalBilledAmountForPatient(patientId);
    }
}