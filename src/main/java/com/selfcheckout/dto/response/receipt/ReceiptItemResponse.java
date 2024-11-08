package com.selfcheckout.dto.response.receipt;

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
public class ReceiptItemResponse {

    private String name;
    private Long quantity;
    private BigDecimal price;
    private Department department;
}
