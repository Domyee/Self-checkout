package com.selfcheckout.controller;

import com.selfcheckout.model.Product;
import com.selfcheckout.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> retrieveProduct(@PathVariable Long id){
        Product product = productService.retrieveProduct(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
