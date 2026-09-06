package com.CV.Hospital.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "admissions")
@Getter
@Setter
@NoArgsConstructor
public class Admission extends BaseClass{

    @Column(nullable = false)
    private LocalDate admitDate;

    private LocalDate dischargeDate;
}
