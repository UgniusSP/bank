package com.accenture.bank.domain.deposit.mapper;

import com.accenture.bank.domain.deposit.dto.DepositRequestDto;
import com.accenture.bank.domain.deposit.dto.DepositResponseDto;
import com.accenture.bank.domain.deposit.entity.Deposit;
import com.accenture.bank.domain.user.entity.User;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DepositMapper {

    public Deposit toDeposit(DepositRequestDto depositRequestDto, User user){
        return Deposit.builder()
                .amount(depositRequestDto.getAmount())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
    }

    public DepositResponseDto toDepositResponseDto(Deposit deposit) {
        return DepositResponseDto.builder()
                .id(deposit.getId())
                .amount(deposit.getAmount())
                .createdAt(deposit.getCreatedAt())
                .accountId(deposit.getUser().getId())
                .build();
    }
}
