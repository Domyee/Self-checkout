package com.selfcheckout.repository.receipt;

import com.selfcheckout.model.receipt.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

//    @Query("SELECT * FROM receipt r WHERE r.createTms BETWEEN :startOfDay AND :endOfDay")
    List<Receipt> findByCreateTmsBetween(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
