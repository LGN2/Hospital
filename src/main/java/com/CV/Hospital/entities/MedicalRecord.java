package com.CV.Hospital.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord extends BaseClass{

    @Column(nullable = false, length = 200)
    private String diagnosis;

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private LocalDate recordDate;
}
