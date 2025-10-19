package com.example.bankcards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.bankcards.repository")
@EntityScan("com.example.bankcards.entity")
public class BankcardsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankcardsApplication.class, args);
    }
}
