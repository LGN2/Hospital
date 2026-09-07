package com.CV.Hospital.controllers;

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
    public Bill addBill(
            @RequestBody Bill bill,
            @RequestParam Long patientId) {

        return billService.addBill(bill, patientId);
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<Bill> getBillsByPatient(
            @PathVariable Long patientId) {

        return billService.getBillsByPatient(patientId);
    }

    @GetMapping("/unpaid")
    public List<Bill> getUnpaidBills() {
        return billService.getUnpaidBills();
    }

    @GetMapping("/patient/{patientId}/unpaid")
    public List<Bill> getUnpaidBillsByPatient(
            @PathVariable Long patientId) {

        return billService.getUnpaidBillsByPatient(patientId);
    }

    @PutMapping("/{id}")
    public Bill updateBill(
            @PathVariable Long id,
            @RequestBody Bill bill) {

        return billService.updateBill(id, bill);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
    }
}
