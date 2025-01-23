package com.accenture.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    @GetMapping
    public String getBank() {
        return "Welcome to the bank";
    }
}
