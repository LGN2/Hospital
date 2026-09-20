package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.BillDTO;
import com.CV.Hospital.entities.Bill;
import com.CV.Hospital.services.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping
    public BillDTO addBill(
            @RequestBody Bill bill,
            @RequestParam Long patientId) {

        return billService.convertToDTO(
                billService.addBill(bill, patientId)
        );
    }

    @GetMapping
    public List<BillDTO> getAllBills() {
        return billService.convertToDTO(
                billService.getAllBills()
        );
    }

    @GetMapping("/{id}")
    public BillDTO getBillById(@PathVariable Long id) {
        return billService.convertToDTO(
                billService.getBillById(id)
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<BillDTO> getBillsByPatient(
            @PathVariable Long patientId) {

        return billService.convertToDTO(
                billService.getBillsByPatient(patientId)
        );
    }

    @GetMapping("/unpaid")
    public List<BillDTO> getUnpaidBills() {
        return billService.convertToDTO(
                billService.getUnpaidBills()
        );
    }

    @GetMapping("/patient/{patientId}/unpaid")
    public List<BillDTO> getUnpaidBillsByPatient(
            @PathVariable Long patientId) {

        return billService.convertToDTO(
                billService.getUnpaidBillsByPatient(patientId)
        );
    }

    @PutMapping("/{id}")
    public BillDTO updateBill(
            @PathVariable Long id,
            @RequestBody Bill bill) {

        return billService.convertToDTO(
                billService.updateBill(id, bill)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
    }
}