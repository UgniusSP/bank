package com.accenture.bank.controller;

import com.accenture.bank.dto.TransactionRequestDto;
import com.accenture.bank.dto.TransactionResponseDto;
import com.accenture.bank.service.TransactionService;
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
            @RequestBody TransactionRequestDto transactionRequestDto) {
        return ResponseEntity.ok(transactionService.createTransaction(transactionRequestDto));
    }

}
