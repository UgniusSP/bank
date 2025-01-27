package com.accenture.bank.domain.auth.mapper;

import com.accenture.bank.domain.auth.dto.RegisterRequest;
import com.accenture.bank.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
@AllArgsConstructor
public class AuthenticationMapper {

    private final PasswordEncoder passwordEncoder;

    public User toUser(RegisterRequest registerRequest) {
        return User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .birthDate(registerRequest.getBirthDate())
                .balance(BigDecimal.valueOf(0.0))
                .build();
    }

}
