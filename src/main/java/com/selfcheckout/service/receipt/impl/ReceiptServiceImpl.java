package com.selfcheckout.service.receipt.impl;

import com.selfcheckout.dto.request.receipt.CreateReceiptReq;
import com.selfcheckout.dto.request.receipt.UpdateReceiptReq;
import com.selfcheckout.dto.response.receipt.CreateReceiptResponse;
import com.selfcheckout.dto.response.receipt.UpdateReceiptResponse;
import com.selfcheckout.factory.receipt.ReceiptFactory;
import com.selfcheckout.model.product.Product;
import com.selfcheckout.model.receipt.Receipt;
import com.selfcheckout.model.receipt.ReceiptTmp;
import com.selfcheckout.model.receipt.ReceiptTmpItem;
import com.selfcheckout.repository.product.ProductRepository;
import com.selfcheckout.repository.receipt.ReceiptRepository;
import com.selfcheckout.repository.receipt.ReceiptTmpRepository;
import com.selfcheckout.service.receipt.ReceiptService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ReceiptTmpRepository receiptTmpRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReceiptFactory receiptFactory;

    @Override
    public CreateReceiptResponse createReceipt(CreateReceiptReq request) {

        Receipt result = receiptRepository.save(
                receiptFactory.mapReceiptEntity(request));

        return receiptFactory.mapCreateReceiptResponse(result);
    }

    @Override
    public CreateReceiptResponse createReceipt(Long receiptTmpId) {

        ReceiptTmp receiptTmp = receiptTmpRepository.getReferenceById(receiptTmpId);

        // save the actual receipt
        Receipt result = receiptRepository.save(
                receiptFactory.mapReceiptEntity(receiptTmp));

        // Delete the temporary receipt and its items
        receiptTmpRepository.deleteById(receiptTmpId);

        return receiptFactory.mapCreateReceiptResponse(result);
    }

    @Override
    public UpdateReceiptResponse updateReceipt(UpdateReceiptReq request) {

        // Retrieve the product from barcode
        Product product = Optional.of(
                productRepository.findByBarcode(request.getBarcode()))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product with barcode %s not found", request.getBarcode())));


        ReceiptTmp receiptTmp = new ReceiptTmp();

        // Check if a Temporary receipt with the request id is present
        if(request.getReceiptTmpId() != null) {
            Optional<ReceiptTmp> optionalReceiptTmp =
                    Optional.of(
                            receiptTmpRepository.findById(request.getReceiptTmpId())
                            .orElseThrow(() -> new EntityNotFoundException("Temporary Receipt with id %d not found: " + request.getReceiptTmpId())));

            receiptTmp = optionalReceiptTmp.get();
        }

        List<ReceiptTmpItem> items = receiptTmp.getItems();

        boolean isNewItem = true;

        // Check if the same item is present (by name)
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getName().equalsIgnoreCase(product.getName())){
                ReceiptTmpItem item = items.get(i);
                item.setQuantity(item.getQuantity() + 1);
                items.set(i, item);
                isNewItem = false;
                break;
            }
        }

        // Add item if it's new
        if(isNewItem)
            items.add(createNewReceiptTmpItem(product, receiptTmp));

        receiptTmp.setItems(items);

        return receiptFactory.mapUpdateReceiptResponse(
                receiptTmpRepository.save(receiptTmp));
    }

    private ReceiptTmpItem createNewReceiptTmpItem(Product product, ReceiptTmp receiptTmp){
        ReceiptTmpItem item = new ReceiptTmpItem();

        item.setName(product.getName());
        item.setQuantity(1L);
        item.setPrice(product.getPrice());
        item.setReceiptTmp(receiptTmp);

        return item;
    }
}
