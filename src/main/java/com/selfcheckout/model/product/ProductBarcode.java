package com.selfcheckout.model.product;

import com.selfcheckout.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "product_barcode")
public class ProductBarcode extends BaseEntity {

    @Column(name = "CODE")
    private String code;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "PRODUCT_ID")
    private Long productId;
}
