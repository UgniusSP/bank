package com.accenture.bank.domain.user.dao;

import com.accenture.bank.domain.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    User getUserById(UUID id);
    void saveUser(User user);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();

}
