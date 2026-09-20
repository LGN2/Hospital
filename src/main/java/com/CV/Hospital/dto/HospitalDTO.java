package com.CV.Hospital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDTO {

    private Long id;

    @NotBlank(message = "Hospital name is required")
    @Size(max = 100, message = "Hospital name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Hospital location is required")
    @Size(max = 150, message = "Hospital location cannot exceed 150 characters")
    private String location;
}