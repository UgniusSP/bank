package com.accenture.bank.domain.bank.controller;

import com.accenture.bank.domain.transaction.dto.TransactionRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bank")
@AllArgsConstructor
public class BankController {

    private RestTemplate restTemplate;

    @GetMapping("/internal")
    public String getBank() {
        return "Welcome from bank of Ugnius!";
    }

    @GetMapping("/external")
    public String getBankExternal() {
        return restTemplate.getForObject("https://bank-8uae.onrender.com/api/v1/bank/internal", String.class);
    }

    @PostMapping("/external")
    public String postBankExternal(@Valid @RequestBody TransactionRequestDto transactionRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TransactionRequestDto> requestEntity = new HttpEntity<>(transactionRequestDto, headers);

        return restTemplate.postForObject(
                "https://bank-8uae.onrender.com/api/v1/bank/internal",
                requestEntity,
                String.class
        );
    }


}
