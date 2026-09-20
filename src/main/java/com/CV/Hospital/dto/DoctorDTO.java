package com.CV.Hospital.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Long id;

    @NotBlank(message = "Doctor name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    @Size(max = 150)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20)
    private String phoneNumber;

    @NotBlank(message = "Specialization is required")
    @Size(max = 100)
    private String specialization;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    @NotNull(message = "Hospital ID is required")
    private Long hospitalId;
}