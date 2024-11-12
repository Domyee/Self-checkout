package com.selfcheckout.model.product;

import com.selfcheckout.model.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "product_barcode")
public class ProductBarcode extends BaseEntity {

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
