package com.accenture.bank.service;

import com.accenture.bank.dto.TransactionRequestDto;
import com.accenture.bank.dto.TransactionResponseDto;

public interface TransactionService {

    TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);

}
