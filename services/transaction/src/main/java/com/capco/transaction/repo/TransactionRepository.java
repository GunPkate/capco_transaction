package com.capco.transaction.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.capco.transaction.model.entity.Transaction;
import com.capco.transaction.model.entity.Transaction.TransactionStatus;

import java.util.List;



public interface TransactionRepository extends CrudRepository<Transaction,String> {

    Transaction findByTransactionId(String transactionId);

    List<Transaction> findAllByStatusIn(List<TransactionStatus> statuses);

    @Modifying
    @Query(value = "UPDATE transactions SET status = 'PROCESSING' WHERE id=:transactionId", nativeQuery = true)
    int updateStatusToProcessing(@Param("transactionId") String transactionId);

    int countByStatus(TransactionStatus status);
    
}
