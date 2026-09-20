package com.CV.Hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionDTO {

    private Long id;
    private LocalDate admitDate;
    private LocalDate dischargeDate;
    private Long patientId;
    private Long roomId;
}