package com.selfcheckout.service.impl;

import com.selfcheckout.model.ProductStock;
import com.selfcheckout.repository.ProductStockRepository;
import com.selfcheckout.service.ProductStockService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductStockServiceImpl implements ProductStockService {

    @Autowired
    private ProductStockRepository productStockRepository;

    @Override
    public ProductStock retrieveProductStock(Long id) {
        Optional<ProductStock> productStock = productStockRepository.findById(id);

        if(productStock.isEmpty())
            throw new EntityNotFoundException(String.format("Stock with id %d not found", id));

        return productStock.get();
    }
}
