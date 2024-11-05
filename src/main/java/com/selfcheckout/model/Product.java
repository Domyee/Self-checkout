package com.selfcheckout.model;

import com.selfcheckout.model.enumeration.Department;
import com.selfcheckout.model.enumeration.MeasurementUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "MEASUREMENT_UNIT")
    @Enumerated(EnumType.STRING)
    private MeasurementUnit measurementUnit;

    @Column(name = "DEPARTMENT")
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "PRICE")
    private BigDecimal price;
}
