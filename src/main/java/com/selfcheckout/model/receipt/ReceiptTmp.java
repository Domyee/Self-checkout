package com.selfcheckout.model.receipt;

import com.selfcheckout.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receipt_tmp")
public class ReceiptTmp extends BaseEntity {

    @ElementCollection
    @Column(name = "items")
    private List<ReceiptItem> itemList;

    @Column(name = "create_tms")
    private LocalDateTime createTms;

}
