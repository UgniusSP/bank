package com.accenture.bank.domain.transaction.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequestDto {

    @Nullable
    private String senderAccountNumber;

    @NotNull(message = "Receiver ID is required")
    private String receiverAccountNumber;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be greater than 0.0")
    private BigDecimal amount;

}
