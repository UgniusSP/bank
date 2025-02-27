package com.accenture.bank.domain.transaction.dao;

import com.accenture.bank.common.exception.InvalidTransactionException;
import com.accenture.bank.domain.transaction.entity.Transaction;
import com.accenture.bank.domain.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionDaoImpl implements TransactionDao {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        validateTransaction(transaction);

        return transactionRepository.save(transaction);
    }

    private void validateTransaction(Transaction transaction) {
        if(transaction == null) {
            throw new InvalidTransactionException("Transaction cannot be null");
        }
    }
}
