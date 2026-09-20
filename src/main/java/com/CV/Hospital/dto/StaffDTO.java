package com.CV.Hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffDTO {

    private Long id;
    private String name;
    private String role;
    private String phoneNumber;
    private Long departmentId;
}