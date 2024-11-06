package com.selfcheckout.model.product;

import com.selfcheckout.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "PRODUCT_STOCK")
public class ProductStock extends BaseEntity {

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "DAY")
    private LocalDate day;

    @Column(name = "PRODUCT_ID")
    private Long productId;
}
