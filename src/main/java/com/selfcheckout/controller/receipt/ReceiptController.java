package com.selfcheckout.controller.receipt;

import com.selfcheckout.dto.enumeration.Department;
import com.selfcheckout.dto.request.receipt.CreateReceiptReq;
import com.selfcheckout.dto.request.receipt.UpdateReceiptReq;
import com.selfcheckout.dto.response.receipt.CreateReceiptResponse;
import com.selfcheckout.dto.response.receipt.UpdateReceiptResponse;
import com.selfcheckout.service.receipt.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/receipt")
@Slf4j
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/")
    public ResponseEntity<CreateReceiptResponse> createReceipt(@RequestBody CreateReceiptReq request){
        CreateReceiptResponse response = receiptService.createReceipt(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{receiptTmpId}")
    public ResponseEntity<CreateReceiptResponse> createReceipt(@PathVariable Long receiptTmpId){
        CreateReceiptResponse response = receiptService.createReceipt(receiptTmpId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<UpdateReceiptResponse> updateReceipt(@RequestBody UpdateReceiptReq request){
        UpdateReceiptResponse response = receiptService.updateReceipt(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/turnover/{day}")
    public ResponseEntity<BigDecimal> retrieveDayTurnover(@PathVariable LocalDate day){
        BigDecimal response = receiptService.retrieveDayTurnover(day);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/department/turnover/{day}")
    public ResponseEntity<Map<Department, BigDecimal>> retrieveDepartmentDayTurnover(@PathVariable LocalDate day){
        Map<Department, BigDecimal> response = receiptService.retrieveDepartmentDayTurnover(day);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/department/turnover/year/{year}")
    public ResponseEntity<Map<Department, BigDecimal>> retrieveDepartmentYearTurnover(@PathVariable int year){
        Map<Department, BigDecimal> response = receiptService.retrieveDepartmentYearTurnover(year);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
