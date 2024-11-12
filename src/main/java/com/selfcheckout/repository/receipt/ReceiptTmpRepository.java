package com.selfcheckout.repository.receipt;

import com.selfcheckout.model.receipt.ReceiptTmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptTmpRepository extends JpaRepository<ReceiptTmp, Long> {
}
