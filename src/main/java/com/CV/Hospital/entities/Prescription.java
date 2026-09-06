package com.CV.Hospital.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
public class Prescription extends BaseClass{

    @Column(nullable = false, length = 150)
    private String medicineName;

    @Column(nullable = false, length = 100)
    private String dosage;
}
