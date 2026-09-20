package com.CV.Hospital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuardianDTO {

    private Long id;

    @NotBlank(message = "Guardian name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Relationship is required")
    @Size(max = 50)
    private String relationship;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20)
    private String phoneNumber;

    @NotNull(message = "Patient ID is required")
    private Long patientId;
}