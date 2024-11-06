package com.selfcheckout.model.product;

import com.selfcheckout.dto.enumeration.Department;
import com.selfcheckout.dto.enumeration.MeasurementUnit;
import com.selfcheckout.model.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "measurement_unit")
    @Enumerated(EnumType.STRING)
    private MeasurementUnit measurementUnit;

    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "price")
    private BigDecimal price;
}
