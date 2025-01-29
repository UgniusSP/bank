package com.accenture.bank.domain.user.service;

import com.accenture.bank.domain.user.dto.UserResponseDto;
import com.accenture.bank.domain.user.dto.UserResponseWithAccountAndBalanceDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto getUserById(UUID id);
    List<UserResponseWithAccountAndBalanceDto> getAllUsersWithAccountAndBalance();

}
