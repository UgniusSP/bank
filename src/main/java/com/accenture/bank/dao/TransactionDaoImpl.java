package com.accenture.bank.dao;

import com.accenture.bank.common.exception.InvalidTransactionException;
import com.accenture.bank.common.exception.InvalidUserException;
import com.accenture.bank.entity.Transaction;
import com.accenture.bank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
        if (transaction.getAmount() == null || transaction.getAmount().compareTo(BigDecimal.valueOf(0.0)) <= 0) {
            throw new InvalidTransactionException("Amount cannot be null or less than or equal to 0");
        }
        if (transaction.getSender() == null) {
            throw new InvalidUserException("Sender account cannot be null");
        }
        if (transaction.getReceiver() == null) {
            throw new InvalidUserException("Receiver account cannot be null");
        }
    }
}
