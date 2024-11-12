package com.selfcheckout;

import com.selfcheckout.controller.product.ProductController;
import com.selfcheckout.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SelfcheckoutApplicationTests {

	@MockBean
	ProductController productController;
	@MockBean
	ProductService productService;

	@Test
	void contextLoads() {
	}
}
