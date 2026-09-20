package com.CV.Hospital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDTO {

    private Long id;

    @NotBlank(message = "Diagnosis is required")
    @Size(max = 200)
    private String diagnosis;

    @Size(max = 1000)
    private String notes;

    @NotNull(message = "Record date is required")
    @PastOrPresent(message = "Record date cannot be in the future")
    private LocalDate recordDate;

    @NotNull(message = "Patient ID is required")
    private Long patientId;
}