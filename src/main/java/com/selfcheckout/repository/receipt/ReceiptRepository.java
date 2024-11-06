package com.selfcheckout.repository.receipt;

import com.selfcheckout.model.receipt.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
