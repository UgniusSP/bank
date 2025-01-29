package com.accenture.bank.domain.user.dao;

import com.accenture.bank.common.exception.InvalidUserException;
import com.accenture.bank.common.exception.InvalidUserIdException;
import com.accenture.bank.domain.user.entity.User;
import com.accenture.bank.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
        if(id == null) {
            throw new InvalidUserIdException("User id cannot be null");
        }

        return userRepository.findById(id)
                .orElseThrow(() -> new InvalidUserException("User not found"));
    }

    @Override
    public User getUserByAccountNumber(String accountNumber) {
        if(accountNumber == null) {
            throw new InvalidUserIdException("Account number cannot be null");
        }

        return userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new InvalidUserException("User not found"));
    }

    @Override
    public void saveUser(User user) {
        if(user == null) {
            throw new InvalidUserException("User cannot be null");
        }

        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        if(email == null) {
            throw new InvalidUserException("Email cannot be null");
        }

        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
