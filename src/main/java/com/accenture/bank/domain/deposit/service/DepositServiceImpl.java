package com.accenture.bank.domain.deposit.service;

import com.accenture.bank.common.exception.InvalidDepositException;
import com.accenture.bank.domain.auth.service.AuthenticationService;
import com.accenture.bank.domain.deposit.dao.DepositDao;
import com.accenture.bank.domain.deposit.dto.DepositRequestDto;
import com.accenture.bank.domain.deposit.dto.DepositResponseDto;
import com.accenture.bank.domain.deposit.mapper.DepositMapper;
import com.accenture.bank.domain.user.dao.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final DepositDao depositDao;
    private final DepositMapper depositMapper;
    private final AuthenticationService authenticationService;
    private final UserDao userDao;

    @Override
    public DepositResponseDto createDeposit(DepositRequestDto depositRequestDto) {
        validateDepositRequest(depositRequestDto);

        var user = authenticationService.getAuthenticatedUser();
        user.setBalance(user.getBalance().add(depositRequestDto.getAmount()));
        userDao.saveUser(user);

        var deposit = depositDao.saveDeposit(depositMapper.toDeposit(depositRequestDto, user));

        return depositMapper.toDepositResponseDto(deposit);
    }

    private void validateDepositRequest(DepositRequestDto depositRequestDto) {
        if(depositRequestDto == null) {
            throw new InvalidDepositException("Deposit request must be provided");
        }
        if(depositRequestDto.getAmount() == null) {
            throw new InvalidDepositException("Deposit amount must be provided");
        }
        if(depositRequestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDepositException("Deposit amount must be greater than 0");
        }
    }

}
