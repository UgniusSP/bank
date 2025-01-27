package com.accenture.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransactionRequestDto {

    private UUID senderId;
    private UUID receiverId;
    private BigDecimal amount;

}
