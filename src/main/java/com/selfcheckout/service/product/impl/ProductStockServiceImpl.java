package com.selfcheckout.service.product.impl;

import com.selfcheckout.model.product.ProductStock;
import com.selfcheckout.model.receipt.Receipt;
import com.selfcheckout.model.receipt.ReceiptItem;
import com.selfcheckout.repository.product.ProductStockRepository;
import com.selfcheckout.repository.receipt.ReceiptRepository;
import com.selfcheckout.service.product.ProductStockService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductStockServiceImpl implements ProductStockService {

    @Autowired
    private ProductStockRepository productStockRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    public ProductStock retrieveProductStock(Long id) {
        Optional<ProductStock> productStock = productStockRepository.findById(id);

        if(productStock.isEmpty())
            throw new EntityNotFoundException(String.format("Stock with id %d not found", id));

        return productStock.get();
    }

    @Override
    @Transactional
    public void calculateStock() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        List<Receipt> receiptList = receiptRepository.findByCreateTmsBetween(startOfDay, endOfDay);

        Map<Long, Long> productQuantitiesSold = receiptList.stream()
                .flatMap(receipt -> receipt.getItems().stream())
                .collect(Collectors.groupingBy(
                        ReceiptItem::getProductId,
                        Collectors.summingLong(ReceiptItem::getQuantity)
                ));

        for(Map.Entry<Long, Long> pq : productQuantitiesSold.entrySet()){
            Long productId = pq.getKey();
            Long productQuantityValue = pq.getValue();

            Optional<ProductStock> productStockOptional =
                    productStockRepository.findByProductId(productId);

            if(productStockOptional.isEmpty())
                throw new EntityNotFoundException(String.format("Stock of Product id %d not found", productId));

            ProductStock productStock = productStockOptional.get();
            long quantity = productStock.getQuantity() - productQuantityValue;

            productStock.setQuantity(quantity);
            productStock.setDay(LocalDate.now());
        }
    }
}
