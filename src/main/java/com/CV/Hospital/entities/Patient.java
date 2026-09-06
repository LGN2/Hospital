package com.CV.Hospital.entities;

import com.CV.Hospital.entities.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient extends BaseClass{

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 20)
    private String gender;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private BloodGroupType bloodGroup;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private List<Guardian> guardians = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private List<Admission> admissions = new ArrayList<>();
}
