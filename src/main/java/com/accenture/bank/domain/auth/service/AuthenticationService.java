package com.accenture.bank.domain.auth.service;

import com.accenture.bank.common.exception.EmailAlreadyInUseException;
import com.accenture.bank.common.exception.InvalidUserException;
import com.accenture.bank.domain.auth.dto.AuthenticationRequest;
import com.accenture.bank.domain.auth.dto.AuthenticationResponse;
import com.accenture.bank.domain.auth.dto.RegisterRequest;
import com.accenture.bank.domain.user.dao.UserDao;
import com.accenture.bank.domain.user.entity.User;
import com.accenture.bank.domain.auth.mapper.AuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDao userDao;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthenticationMapper authenticationMapper;

    public void register(RegisterRequest request) {
        var existingUser = userDao.getUserByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyInUseException("Email is already in use");
        }

        userDao.saveUser(authenticationMapper.toUser(request));
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user =
                userDao
                        .getUserByEmail(request.getEmail())
                        .orElseThrow(() -> new InvalidUserException("User not found"));

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated.");
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User user)) {
            throw new IllegalStateException("Authenticated principal is not a User.");
        }

        return user;
    }
}
