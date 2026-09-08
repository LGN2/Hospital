package com.CV.Hospital.dto;

import com.CV.Hospital.entities.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;
    private String name;
    private String gender;
    private String phoneNumber;
    private BloodGroupType bloodGroup;
    private Long hospitalId;
}