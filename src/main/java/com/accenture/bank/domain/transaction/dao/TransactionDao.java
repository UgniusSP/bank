package com.accenture.bank.domain.transaction.dao;

import com.accenture.bank.domain.transaction.entity.Transaction;

public interface TransactionDao {

    Transaction saveTransaction(Transaction transaction);

}
