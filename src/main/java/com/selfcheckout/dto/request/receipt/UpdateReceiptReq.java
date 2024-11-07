package com.selfcheckout.dto.request.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReceiptReq {

    private Long receiptTmpId;
    private String barcode;

}
