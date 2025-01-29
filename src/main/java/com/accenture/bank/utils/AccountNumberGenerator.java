package com.accenture.bank.utils;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountNumberGenerator {

    public String generateAccountNumber(String firstName, String lastName) {
        return firstName.substring(0, 2).toUpperCase() + lastName.substring(0, 2).toUpperCase() +
                "_" + generateRandomLetters() + (int) (Math.random() * 1000);
    }

    private String generateRandomLetters() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int index = (int) (Math.random() * alphabet.length());
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }

}
