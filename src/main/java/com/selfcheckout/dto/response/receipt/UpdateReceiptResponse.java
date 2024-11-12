package com.selfcheckout.dto.response.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReceiptResponse {

    private Long receiptId;
    private List<ReceiptItemResponse> items;
}
