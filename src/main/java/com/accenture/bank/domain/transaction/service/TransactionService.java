package com.accenture.bank.domain.transaction.service;

import com.accenture.bank.domain.transaction.dto.TransactionRequestDto;
import com.accenture.bank.domain.transaction.dto.TransactionResponseDto;

public interface TransactionService {

    TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);

}
