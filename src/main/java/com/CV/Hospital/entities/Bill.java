package com.CV.Hospital.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
public class Bill extends BaseClass{

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
}
