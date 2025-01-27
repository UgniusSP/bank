package com.accenture.bank.dto;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private BigDecimal amount;
    private LocalDateTime createdAt;

}
