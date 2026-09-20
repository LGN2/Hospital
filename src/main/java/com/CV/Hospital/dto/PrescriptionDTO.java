package com.CV.Hospital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO {

    private Long id;

    @NotBlank(message = "Medicine name is required")
    @Size(max = 150)
    private String medicineName;

    @NotBlank(message = "Dosage is required")
    @Size(max = 100)
    private String dosage;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be greater than 0")
    private Integer durationDays;

    @NotNull(message = "Medical record ID is required")
    private Long medicalRecordId;
}