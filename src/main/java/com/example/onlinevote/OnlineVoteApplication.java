package com.example.onlinevote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class OnlineVoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineVoteApplication.class, args);
    }

}
