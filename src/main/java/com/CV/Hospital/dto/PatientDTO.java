package com.CV.Hospital.dto;

import com.CV.Hospital.entities.type.BloodGroupType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;

    @NotBlank(message = "Patient name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Gender is required")
    @Size(max = 20)
    private String gender;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20)
    private String phoneNumber;

    @NotNull(message = "Blood group is required")
    private BloodGroupType bloodGroup;

    @NotNull(message = "Hospital ID is required")
    private Long hospitalId;
}