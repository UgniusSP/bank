package com.accenture.bank.domain.user.service;

import com.accenture.bank.common.exception.InvalidUserIdException;
import com.accenture.bank.domain.user.dao.UserDao;
import com.accenture.bank.domain.user.dto.UserResponseDto;
import com.accenture.bank.domain.user.dto.UserResponseWithAccountAndBalanceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public UserResponseDto getUserById(UUID id) {
        validateUserId(id);

        var user = userDao.getUserById(id);

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .balance(user.getBalance())
                .birthDate(user.getBirthDate())
                .build();
    }

    @Override
    public List<UserResponseWithAccountAndBalanceDto> getAllUsersWithAccountAndBalance() {
        return userDao.getAllUsers().stream()
                .map(user -> UserResponseWithAccountAndBalanceDto.builder()
                        .accountNumber(user.getAccountNumber())
                        .balance(user.getBalance())
                        .build())
                .toList();
    }

    private void validateUserId(UUID id) {
        if(id == null) {
            throw new InvalidUserIdException("User id must be provided");
        }
    }


}
