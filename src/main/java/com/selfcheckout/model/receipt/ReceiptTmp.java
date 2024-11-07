package com.selfcheckout.model.receipt;

import com.selfcheckout.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipt_tmp")
@Getter
@Setter
@NoArgsConstructor
public class ReceiptTmp extends BaseEntity {

    @Column(name = "items")
    @OneToMany(mappedBy = "receiptTmp", cascade = CascadeType.ALL)
    private List<ReceiptTmpItem> items = new ArrayList<>();

    @Column(name = "create_tms")
    private LocalDateTime createTms;

}
