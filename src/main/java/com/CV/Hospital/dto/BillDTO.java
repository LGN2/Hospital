package com.CV.Hospital.dto;

import com.CV.Hospital.entities.type.BillStatusType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {

    private Long id;

    @NotNull(message = "Bill amount is required")
    @Positive(message = "Bill amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Bill status is required")
    private BillStatusType status;

    @NotNull(message = "Bill date is required")
    @PastOrPresent(message = "Bill date cannot be in the future")
    private LocalDate billDate;

    @NotNull(message = "Patient ID is required")
    private Long patientId;
}