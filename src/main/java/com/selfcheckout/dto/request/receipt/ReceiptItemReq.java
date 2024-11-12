package com.selfcheckout.dto.request.receipt;

import com.selfcheckout.dto.enumeration.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptItemReq {

    private String name;
    private Long quantity;
    private BigDecimal price;
    private Department department;
}
