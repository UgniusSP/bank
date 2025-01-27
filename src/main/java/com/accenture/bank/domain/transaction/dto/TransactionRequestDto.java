package com.accenture.bank.domain.transaction.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransactionRequestDto {

    @NotNull(message = "Receiver ID is required")
    private UUID receiverId;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be greater than 0.0")
    private BigDecimal amount;

}
