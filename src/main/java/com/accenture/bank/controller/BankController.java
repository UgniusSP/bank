package com.accenture.bank.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/bank")
@AllArgsConstructor
public class BankController {

    private final RestTemplate restTemplate;

    @GetMapping("/internal")
    public String getBank() {
        return "Welcome to the bank";
    }

    @GetMapping("/external")
    public String getBankExternal() {
        return restTemplate.getForObject("https://bank-e1n6.onrender.com/api/v1/bank", String.class);
    }

}
