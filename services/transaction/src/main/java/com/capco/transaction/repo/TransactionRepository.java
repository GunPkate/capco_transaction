package com.capco.transaction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capco.transaction.model.entity.Transaction;
import com.capco.transaction.model.entity.Transaction.TransactionStatus;

import java.util.List;



public interface TransactionRepository extends JpaRepository<Transaction,String> {

    Transaction findByTransactionId(String transactionId);

    List<Transaction> findAllByStatusIn(List<TransactionStatus> statuses);

    @Modifying
    @Transactional
    @Query(value = "UPDATE transactions SET status=:status WHERE transaction_id=:transactionId", nativeQuery = true)
    int updateStatusValueById(@Param("status") String status, @Param("transactionId") String transactionId);

    int countByStatus(TransactionStatus status);
    
}
