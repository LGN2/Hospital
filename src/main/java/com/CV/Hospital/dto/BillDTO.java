package com.CV.Hospital.dto;

import com.CV.Hospital.entities.type.BillStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {

    private Long id;
    private BigDecimal amount;
    private BillStatusType status;
    private LocalDate billDate;
    private Long patientId;
}