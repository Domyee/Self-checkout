package com.selfcheckout.factory.receipt;

import com.selfcheckout.dto.request.receipt.CreateReceiptReq;
import com.selfcheckout.dto.request.receipt.ReceiptItemReq;
import com.selfcheckout.dto.response.receipt.CreateReceiptResponse;
import com.selfcheckout.dto.response.receipt.ReceiptItemResponse;
import com.selfcheckout.dto.response.receipt.UpdateReceiptResponse;
import com.selfcheckout.model.receipt.Receipt;
import com.selfcheckout.model.receipt.ReceiptItem;
import com.selfcheckout.model.receipt.ReceiptTmp;
import com.selfcheckout.model.receipt.ReceiptTmpItem;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReceiptFactory {

    // CreateReceiptReq -> Receipt
    public Receipt mapReceiptEntity(CreateReceiptReq request){

        Receipt receipt = new Receipt();
        List<ReceiptItem> items = new ArrayList<>();

        for(ReceiptItemReq ri : request.getItems()){
            ReceiptItem item = new ReceiptItem();

            item.setName(ri.getName());
            item.setPrice(ri.getPrice());
            item.setQuantity(ri.getQuantity());
            item.setDepartment(ri.getDepartment());
            item.setReceipt(receipt);

            items.add(item);
        }

        receipt.setItems(items);
        receipt.setCreateTms(LocalDateTime.now());

        return receipt;
    }

    // ReceiptTmp -> Receipt
    public Receipt mapReceiptEntity(ReceiptTmp request){

        Receipt receipt = new Receipt();
        List<ReceiptItem> items = new ArrayList<>();

        for(ReceiptTmpItem ri : request.getItems()){
            ReceiptItem item = new ReceiptItem();

            item.setName(ri.getName());
            item.setPrice(ri.getPrice());
            item.setQuantity(ri.getQuantity());
            item.setDepartment(ri.getDepartment());
            item.setReceipt(receipt);

            items.add(item);
        }

        receipt.setItems(items);
        receipt.setCreateTms(LocalDateTime.now());

        return receipt;
    }


    // Receipt -> CreateReceiptResponse
    public CreateReceiptResponse mapCreateReceiptResponse(Receipt receipt){

        CreateReceiptResponse response = new CreateReceiptResponse();
        List<ReceiptItemResponse> items = new ArrayList<>();

        for(ReceiptItem ri : receipt.getItems()){
            ReceiptItemResponse item = new ReceiptItemResponse();

            item.setName(ri.getName());
            item.setQuantity(ri.getQuantity());
            item.setPrice(ri.getPrice());
            item.setDepartment(ri.getDepartment());

            items.add(item);
        }

        response.setItems(items);
        response.setCreateTms(receipt.getCreateTms());

        return response;
    }

    // ReceiptTmp -> UpdateReceiptResponse
    public UpdateReceiptResponse mapUpdateReceiptResponse(ReceiptTmp receiptTmp){
        UpdateReceiptResponse response = new UpdateReceiptResponse();
        List<ReceiptItemResponse> items = new ArrayList<>();

        for(ReceiptTmpItem ri : receiptTmp.getItems()){
            ReceiptItemResponse item = new ReceiptItemResponse();

            item.setName(ri.getName());
            item.setQuantity(ri.getQuantity());
            item.setPrice(ri.getPrice());
            item.setDepartment(ri.getDepartment());

            items.add(item);
        }

        response.setItems(items);
        response.setReceiptId(receiptTmp.getId());

        return response;
    }
}
