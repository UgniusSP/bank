package com.accenture.bank.domain.transaction.mapper;

import com.accenture.bank.domain.transaction.dto.TransactionRequestDto;
import com.accenture.bank.domain.transaction.dto.TransactionResponseDto;
import com.accenture.bank.domain.transaction.entity.Transaction;
import com.accenture.bank.domain.user.entity.User;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class TransactionMapper {

    public Transaction toTransaction(TransactionRequestDto transactionRequestDto, User sender) {
        return Transaction.builder()
                .sender(sender)
                .senderAccountNumber(sender.getAccountNumber())
                .receiverAccountNumber(transactionRequestDto.getReceiverAccountNumber())
                .amount(transactionRequestDto.getAmount())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public TransactionResponseDto toTransactionResponseDto(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .email(transaction.getSender().getEmail())
                .senderAccountNumber(transaction.getSender().getAccountNumber())
                .receiverAccountNumber(transaction.getReceiverAccountNumber())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .build();
    }

}
