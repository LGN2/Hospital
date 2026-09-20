package com.CV.Hospital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffDTO {

    private Long id;

    @NotBlank(message = "Staff name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Role is required")
    @Size(max = 50)
    private String role;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20)
    private String phoneNumber;

    @NotNull(message = "Department ID is required")
    private Long departmentId;
}