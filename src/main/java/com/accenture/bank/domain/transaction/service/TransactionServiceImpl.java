package com.accenture.bank.domain.transaction.service;

import com.accenture.bank.common.exception.InvalidTransactionException;
import com.accenture.bank.common.exception.InvalidUserException;
import com.accenture.bank.common.exception.InvalidUserIdException;
import com.accenture.bank.domain.auth.service.AuthenticationService;
import com.accenture.bank.domain.transaction.dao.TransactionDao;
import com.accenture.bank.domain.transaction.dto.TransactionRequestDto;
import com.accenture.bank.domain.transaction.dto.TransactionResponseDto;
import com.accenture.bank.domain.transaction.mapper.TransactionMapper;
import com.accenture.bank.domain.user.dao.UserDao;
import com.accenture.bank.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;
    private final UserDao userDao;
    private final TransactionMapper transactionMapper;
    private final AuthenticationService authenticationService;

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        var user = authenticationService.getAuthenticatedUser();

        validateTransaction(transactionRequestDto, user);

        var amount = transactionRequestDto.getAmount();

        var sender = userDao.getUserByAccountNumber(user.getAccountNumber());
        validateFunds(sender, amount);

        User receiver;
        try {
            receiver = userDao.getUserByAccountNumber(transactionRequestDto.getReceiverAccountNumber());
        } catch (InvalidUserException e) {
            return sendMoneyToExternalBank(transactionRequestDto, sender);
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        userDao.saveUser(sender);
        userDao.saveUser(receiver);

        var transaction = transactionDao.saveTransaction(transactionMapper.toTransaction(transactionRequestDto, sender));

        return transactionMapper.toTransactionResponseDto(transaction);
    }

    private TransactionResponseDto sendMoneyToExternalBank(TransactionRequestDto transactionRequestDto, User sender) {
        RestTemplate restTemplate = new RestTemplate();

        String receiverUrl = "https://bank-8uae.onrender.com/api/v1/transactions/receive";

        transactionRequestDto.setSenderAccountNumber(sender.getAccountNumber());
        transactionRequestDto.setReceiverAccountNumber(transactionRequestDto.getReceiverAccountNumber());

        System.out.println(transactionRequestDto.getReceiverAccountNumber());

        ResponseEntity<TransactionResponseDto> response = restTemplate.postForEntity(receiverUrl, transactionRequestDto, TransactionResponseDto.class);
        System.out.println(response);

        if (response.getStatusCode().is2xxSuccessful()) {
            sender.setBalance(sender.getBalance().subtract(transactionRequestDto.getAmount()));

            var receiverAccountNumber = Objects.requireNonNull(response.getBody()).getReceiverAccountNumber();
            var receiver = User.builder().accountNumber(receiverAccountNumber).build();
            receiver.setBalance(transactionRequestDto.getAmount());

            var transaction = transactionDao.saveTransaction(transactionMapper.toTransaction(transactionRequestDto, sender));

            return transactionMapper.toTransactionResponseDto(transaction);
        } else {
            throw new RestClientException("Failed to send money to external bank");
        }
    }

    private void validateTransaction(TransactionRequestDto transactionRequestDto, User user) {
        if(user == null || transactionRequestDto.getReceiverAccountNumber() == null) {
            throw new InvalidUserIdException("Sender and receiver cannot be null");
        }
        if (user.getAccountNumber().equals(transactionRequestDto.getReceiverAccountNumber())) {
            throw new InvalidTransactionException("Sender and receiver cannot be the same");
        }
        if (transactionRequestDto.getAmount() == null || transactionRequestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Amount must not be empty or be greater than 0");
        }
    }

    private void validateFunds(User sender, BigDecimal amount) {
        if(sender.getBalance() == null) {
            throw new InvalidTransactionException("Sender balance is null");
        }

        if (sender.getBalance().compareTo(amount) <= 0) {
            throw new InvalidTransactionException("Insufficient funds");
        }
    }

}
