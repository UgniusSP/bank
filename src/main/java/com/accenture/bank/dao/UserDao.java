package com.accenture.bank.dao;

import com.accenture.bank.entity.User;

import java.util.UUID;

public interface UserDao {

    User getUserById(UUID id);
    void saveUser(User user);

}
