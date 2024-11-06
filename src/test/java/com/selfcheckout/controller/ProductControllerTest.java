package com.selfcheckout.controller;

import com.selfcheckout.service.ProductService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ProductControllerTest {

    @MockBean
    ProductController productController;
    @MockBean
    ProductService productService;

}