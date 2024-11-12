package com.selfcheckout.model.receipt;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiptItemSold {
    
    private long productId;
    private long totalQuantity;
}
