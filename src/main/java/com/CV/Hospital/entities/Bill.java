package com.CV.Hospital.entities;

import com.CV.Hospital.entities.type.BillStatusType;
import jakarta.persistence.*;
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


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BillStatusType status;
}
