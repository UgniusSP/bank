package com.accenture.bank.domain.user.controller;

import com.accenture.bank.domain.user.dto.UserResponseWithAccountAndBalanceDto;
import com.accenture.bank.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseWithAccountAndBalanceDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsersWithAccountAndBalance());
    }

}
