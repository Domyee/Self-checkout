package com.selfcheckout.model.product;

import com.selfcheckout.dto.enumeration.Department;
import com.selfcheckout.dto.enumeration.MeasurementUnit;
import com.selfcheckout.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "barcodes")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductBarcode> barcodes;
}
