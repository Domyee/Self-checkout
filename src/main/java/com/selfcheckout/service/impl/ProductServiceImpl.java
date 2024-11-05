package com.selfcheckout.service.impl;

import com.selfcheckout.model.Product;
import com.selfcheckout.repository.ProductRepository;
import com.selfcheckout.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product retrieveProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty())
            throw new EntityNotFoundException(String.format("Product with id %d not found", id));

        return product.get();
    }
}
