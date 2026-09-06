package com.CV.Hospital.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

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

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToMany(mappedBy = "medicalRecord")
    private List<Prescription> prescriptions = new ArrayList<>();
}
