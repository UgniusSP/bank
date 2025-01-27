package com.accenture.bank.domain.deposit.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositRequestDto {

    private BigDecimal amount;

}
