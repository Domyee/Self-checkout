package com.selfcheckout.service.receipt;

import com.selfcheckout.dto.request.receipt.CreateReceiptReq;
import com.selfcheckout.dto.request.receipt.UpdateReceiptReq;
import com.selfcheckout.dto.response.receipt.CreateReceiptResponse;
import com.selfcheckout.dto.response.receipt.UpdateReceiptResponse;

public interface ReceiptService {

    CreateReceiptResponse createReceipt(CreateReceiptReq receipt);

    UpdateReceiptResponse updateReceipt(UpdateReceiptReq receipt);
}
