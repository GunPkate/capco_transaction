package com.capco.transaction.repo;

import org.springframework.data.repository.CrudRepository;
import com.capco.transaction.model.entity.Transaction;
import com.capco.transaction.model.entity.Transaction.TransactionStatus;

import java.util.List;
import java.util.Optional;


public interface TransactionRepository extends CrudRepository<Transaction,String> {

    Transaction findByTransactionId(String transactionId);

    Optional<Transaction> findByStatusIn(List<TransactionStatus> statuses);

    
}
