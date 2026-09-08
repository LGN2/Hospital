package com.CV.Hospital.dto;

import com.CV.Hospital.entities.type.AppointmentStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Long id;
    private LocalDateTime appointmentDate;
    private String reason;
    private AppointmentStatusType status;
    private Long doctorId;
    private Long patientId;
}