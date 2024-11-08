package com.selfcheckout.model.receipt;

import com.selfcheckout.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receipt")
@Getter
@Setter
@NoArgsConstructor
public class Receipt extends BaseEntity {

    @Column(name = "items")
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<ReceiptItem> items;

    @Column(name = "create_tms")
    private LocalDateTime createTms;

    @Column(name = "total")
    private BigDecimal total;
}
