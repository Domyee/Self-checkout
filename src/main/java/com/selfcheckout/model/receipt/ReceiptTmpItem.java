package com.selfcheckout.model.receipt;

import com.selfcheckout.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "receipt_tmp_item")
@Getter
@Setter
@NoArgsConstructor
public class ReceiptTmpItem extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "receipt_tmp_id")
    private ReceiptTmp receiptTmp;
}
