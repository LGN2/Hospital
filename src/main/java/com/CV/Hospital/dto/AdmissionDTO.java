package com.CV.Hospital.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionDTO {

    private Long id;

    @NotNull(message = "Admission date is required")
    @PastOrPresent(message = "Admission date cannot be in the future")
    private LocalDate admitDate;

    @PastOrPresent(message = "Discharge date cannot be in the future")
    private LocalDate dischargeDate;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Room ID is required")
    private Long roomId;
}