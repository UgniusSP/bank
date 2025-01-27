package com.accenture.bank.domain.transaction.controller;

import com.accenture.bank.domain.transaction.dto.TransactionRequestDto;
import com.accenture.bank.domain.transaction.dto.TransactionResponseDto;
import com.accenture.bank.domain.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/transactions")
@RestController
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDto> createTransaction(
            @Valid @RequestBody TransactionRequestDto transactionRequestDto) {
        return ResponseEntity.ok(transactionService.createTransaction(transactionRequestDto));
    }

}
