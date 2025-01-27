package com.accenture.bank.service;

import com.accenture.bank.common.exception.InvalidTransactionException;
import com.accenture.bank.common.exception.InvalidUserIdException;
import com.accenture.bank.dao.TransactionDao;
import com.accenture.bank.dao.UserDao;
import com.accenture.bank.dto.TransactionRequestDto;
import com.accenture.bank.dto.TransactionResponseDto;
import com.accenture.bank.entity.User;
import com.accenture.bank.mapper.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;
    private final UserDao userDao;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        validateTransaction(transactionRequestDto);

        var amount = transactionRequestDto.getAmount();

        var sender = userDao.getUserById(transactionRequestDto.getSenderId());
        validateFunds(sender, amount);

        var receiver = userDao.getUserById(transactionRequestDto.getReceiverId());

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        userDao.saveUser(sender);
        userDao.saveUser(receiver);

        var transaction = transactionDao.saveTransaction(transactionMapper.toTransaction(transactionRequestDto, sender, receiver));

        return transactionMapper.toTransactionResponseDto(transaction);
    }

    private void validateTransaction(TransactionRequestDto transactionRequestDto) {
        if(transactionRequestDto.getSenderId() == null || transactionRequestDto.getReceiverId() == null) {
            throw new InvalidUserIdException("Sender and receiver cannot be null");
        }
        if (transactionRequestDto.getSenderId().equals(transactionRequestDto.getReceiverId())) {
            throw new InvalidTransactionException("Sender and receiver cannot be the same");
        }
        if (transactionRequestDto.getAmount() == null || transactionRequestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Amount must not be empty or be greater than 0");
        }
    }

    private void validateFunds(User sender, BigDecimal amount) {
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InvalidTransactionException("Insufficient funds");
        }
    }

}
