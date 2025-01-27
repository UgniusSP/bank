package com.accenture.bank.dao;

import com.accenture.bank.entity.Transaction;

public interface TransactionDao {

    Transaction saveTransaction(Transaction transaction);

}
