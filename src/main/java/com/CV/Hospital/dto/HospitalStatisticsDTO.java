package com.CV.Hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalStatisticsDTO {

    private Long hospitalId;
    private String hospitalName;

    private Long activeDepartments;
    private Long activeDoctors;
    private Long activePatients;
}