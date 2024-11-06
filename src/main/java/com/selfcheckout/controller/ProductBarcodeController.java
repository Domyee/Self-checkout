package com.selfcheckout.controller;

import com.selfcheckout.model.ProductBarcode;
import com.selfcheckout.service.ProductBarcodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/barcode")
@Slf4j
public class ProductBarcodeController {

    @Autowired
    private ProductBarcodeService productBarcodeService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductBarcode> retrieveProductBarcode(@PathVariable Long id){
        ProductBarcode productBarcode = productBarcodeService.retrieveProductBarcode(id);

        return new ResponseEntity<>(productBarcode, HttpStatus.OK);
    }

}
