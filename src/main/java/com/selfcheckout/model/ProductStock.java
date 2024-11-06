package com.selfcheckout.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PRODUCT_STOCK")
@Getter
@Setter
@NoArgsConstructor
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "DAY")
    private LocalDate day;

    @Column(name = "PRODUCT_ID")
    private Long productId;
}
