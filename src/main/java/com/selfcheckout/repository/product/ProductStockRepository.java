package com.selfcheckout.repository.product;

import com.selfcheckout.model.product.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
}
