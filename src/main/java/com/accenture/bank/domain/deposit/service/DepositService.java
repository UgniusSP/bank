package com.accenture.bank.domain.deposit.service;

import com.accenture.bank.domain.deposit.dto.DepositRequestDto;
import com.accenture.bank.domain.deposit.dto.DepositResponseDto;

public interface DepositService {

    DepositResponseDto createDeposit(DepositRequestDto depositRequestDto);

}
