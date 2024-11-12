package com.selfcheckout.model.receipt;

import com.selfcheckout.dto.enumeration.Department;
import com.selfcheckout.model.BaseEntity;
import com.selfcheckout.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "receipt_item")
@Getter
@Setter
@NoArgsConstructor
public class ReceiptItem extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getProductId() {
        return product.getId(); // Assuming product has a getter for ID
    }

}
