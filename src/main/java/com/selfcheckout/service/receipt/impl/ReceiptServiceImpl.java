package com.selfcheckout.service.receipt.impl;

import com.selfcheckout.dto.request.receipt.CreateReceiptReq;
import com.selfcheckout.dto.request.receipt.ReceiptItemReq;
import com.selfcheckout.dto.response.receipt.CreateReceiptResponse;
import com.selfcheckout.model.receipt.Receipt;
import com.selfcheckout.model.receipt.ReceiptItem;
import com.selfcheckout.repository.receipt.ReceiptRepository;
import com.selfcheckout.service.receipt.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    public CreateReceiptResponse createReceipt(CreateReceiptReq request) {

        CreateReceiptResponse response = new CreateReceiptResponse();
        Receipt receipt = new Receipt();
        List<ReceiptItem> items = new ArrayList<>();

        for(ReceiptItemReq ri : request.getItems()){
            ReceiptItem receiptItem = new ReceiptItem();

            receiptItem.setName(ri.getName());
            receiptItem.setPrice(ri.getPrice());
            receiptItem.setQuantity(ri.getQuantity());
            receiptItem.setReceipt(receipt);

            items.add(receiptItem);
        }

        receipt.setItems(items);
        receipt.setCreateTms(LocalDateTime.now());

        Receipt result = receiptRepository.save(receipt);

        //TODO Creare factory
//        response.setItems(result.getItems());
        response.setCreateTms(result.getCreateTms());
        return response;
    }
}
