package com.accenture.bank.domain.deposit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositResponseDto {

    private UUID id;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private UUID accountId;

}
