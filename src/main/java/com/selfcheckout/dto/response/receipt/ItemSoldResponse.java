package com.selfcheckout.dto.response.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSoldResponse {

    private Long productId;
    private String productName;
    private Long quantitySold;
    private BigDecimal totalAmount;
}
