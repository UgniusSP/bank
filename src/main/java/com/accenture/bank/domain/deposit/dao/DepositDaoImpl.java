package com.accenture.bank.domain.deposit.dao;

import com.accenture.bank.common.exception.InvalidDepositException;
import com.accenture.bank.domain.deposit.entity.Deposit;
import com.accenture.bank.domain.deposit.repository.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepositDaoImpl implements DepositDao {

    private final DepositRepository depositRepository;

    @Override
    public Deposit saveDeposit(Deposit deposit) {
        validateDeposit(deposit);

        return depositRepository.save(deposit);
    }

    private void validateDeposit(Deposit deposit) {
        if(deposit == null) {
            throw new InvalidDepositException("Deposit is null");
        }
    }

}
