package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty("server.port", "1488");
        System.setProperty("server.address", "0.0.0.0");
        SpringApplication.run(Main.class, args);
    }
}