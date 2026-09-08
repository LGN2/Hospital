package com.CV.Hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String specialization;
    private Long departmentId;
    private Long hospitalId;
}