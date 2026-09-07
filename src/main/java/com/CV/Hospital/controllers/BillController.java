package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Bill;
import com.CV.Hospital.services.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
