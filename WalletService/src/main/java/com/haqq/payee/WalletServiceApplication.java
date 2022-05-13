package com.haqq.payee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WalletServiceApplication {

    public static void main(String[] args) {
        System.out.println("Starting Wallet Service");
        SpringApplication.run(WalletServiceApplication.class, args);
    }

}
