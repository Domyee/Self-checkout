package com.selfcheckout.service.product.impl;

import com.selfcheckout.model.product.ProductBarcode;
import com.selfcheckout.repository.product.ProductBarcodeRepository;
import com.selfcheckout.service.product.ProductBarcodeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductBarcodeServiceImpl implements ProductBarcodeService {

    @Autowired
    private ProductBarcodeRepository productBarcodeRepository;

    @Override
    public ProductBarcode retrieveProductBarcode(Long id) {
        Optional<ProductBarcode> productBarcode = productBarcodeRepository.findById(id);

        if(productBarcode.isEmpty())
            throw new EntityNotFoundException(String.format("Barcode with id %d not found", id));

        return productBarcode.get();
    }
}
