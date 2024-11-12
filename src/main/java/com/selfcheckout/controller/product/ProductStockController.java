package com.selfcheckout.controller.product;

import com.selfcheckout.model.product.ProductStock;
import com.selfcheckout.service.product.ProductStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/stock")
@Slf4j
public class ProductStockController {

    @Autowired
    private ProductStockService productStockService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductStock> retrieveProductBarcode(@PathVariable Long id){
        ProductStock productStock = productStockService.retrieveProductStock(id);

        return new ResponseEntity<>(productStock, HttpStatus.OK);
    }

    @GetMapping("/calculate")
    public ResponseEntity<Void> calculateStock(){
        productStockService.calculateStock();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
