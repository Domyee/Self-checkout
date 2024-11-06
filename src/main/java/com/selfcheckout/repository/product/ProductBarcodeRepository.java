package com.selfcheckout.repository.product;

import com.selfcheckout.model.product.ProductBarcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBarcodeRepository extends JpaRepository<ProductBarcode, Long> {
}
