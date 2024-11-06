package com.selfcheckout.controller.receipt;

import com.selfcheckout.dto.request.receipt.CreateReceiptReq;
import com.selfcheckout.dto.response.receipt.CreateReceiptResponse;
import com.selfcheckout.service.receipt.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
