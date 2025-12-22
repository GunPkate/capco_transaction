package com.capco.transaction.repo;

import org.springframework.data.repository.CrudRepository;
import com.capco.transaction.model.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction,String> {

    Object findByTransactionId(String transactionId);

}
