package com.accenture.bank.domain.deposit.dao;

import com.accenture.bank.domain.deposit.entity.Deposit;

public interface DepositDao {

    Deposit saveDeposit(Deposit deposit);

}
