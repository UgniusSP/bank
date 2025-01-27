package com.accenture.bank.mapper;

import com.accenture.bank.dto.TransactionRequestDto;
import com.accenture.bank.dto.TransactionResponseDto;
import com.accenture.bank.entity.Transaction;
import com.accenture.bank.entity.User;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class TransactionMapper {

    public Transaction toTransaction(TransactionRequestDto transactionRequestDto, User sender, User receiver) {
        return Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(transactionRequestDto.getAmount())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public TransactionResponseDto toTransactionResponseDto(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .senderId(transaction.getSender().getId())
                .receiverId(transaction.getReceiver().getId())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .build();
    }

}
