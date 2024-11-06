package com.selfcheckout.repository;

import com.selfcheckout.model.ProductBarcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBarcodeRepository extends JpaRepository<ProductBarcode, Long> {
}
