package com.capco.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capco.transaction.model.entity.Transaction;
import com.capco.transaction.repo.TransactionRepository;

@SpringBootTest
class TransactionApplicationTests {

	@Autowired
    private TransactionRepository transactionRepository;
	@Test
    void testTransactionSave() {
      
        // transactionRepository.save(new Transaction("TXN_1", "PENDING"));
        
        // paymentService.markStatusValueById("TXN_1", "PROCESSED", "");
        
        // // 3. Verify
        // String status = repository.findById("TXN_1").get().getStatus();
        // assertEquals("PROCESSED", status);
    }
}
