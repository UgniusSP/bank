package com.accenture.bank.domain.deposit.controller;

import com.accenture.bank.domain.deposit.dto.DepositRequestDto;
import com.accenture.bank.domain.deposit.dto.DepositResponseDto;
import com.accenture.bank.domain.deposit.service.DepositService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/deposit")
@RestController
@AllArgsConstructor
public class DepositController {

    private final DepositService depositService;

    @PostMapping
    public ResponseEntity<DepositResponseDto> createDeposit(
            @Valid @RequestBody DepositRequestDto depositRequestDto) {
        return ResponseEntity.ok(depositService.createDeposit(depositRequestDto));
    }


}
