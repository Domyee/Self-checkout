package com.selfcheckout.repository.product;

import com.selfcheckout.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p JOIN p.barcodes b WHERE b.barcode = :barcode")
    Product findByBarcode(@Param("barcode") String barcode);
}
