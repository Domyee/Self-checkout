package com.selfcheckout.service.receipt;

import com.selfcheckout.dto.request.receipt.CreateReceiptReq;
import com.selfcheckout.dto.response.receipt.CreateReceiptResponse;

public interface ReceiptService {

    CreateReceiptResponse createReceipt(CreateReceiptReq receipt);
}
