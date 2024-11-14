package com.selfcheckout.service.receipt;

import com.selfcheckout.dto.enumeration.Department;
import com.selfcheckout.dto.request.receipt.CreateReceiptReq;
import com.selfcheckout.dto.request.receipt.UpdateReceiptReq;
import com.selfcheckout.dto.response.receipt.CreateReceiptResponse;
import com.selfcheckout.dto.response.receipt.UpdateReceiptResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface ReceiptService {

    CreateReceiptResponse createReceipt(CreateReceiptReq receipt);

    CreateReceiptResponse createReceipt(Long receiptTmpId);

    UpdateReceiptResponse updateReceipt(UpdateReceiptReq receipt);

    BigDecimal retrieveDayTurnover(LocalDate day);

    Map<Department, BigDecimal> retrieveDepartmentDayTurnover(LocalDate day);

    Map<Department, BigDecimal> retrieveDepartmentYearTurnover(int year);
}
