package com.hoop2work.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hoop2work.backend.controller",
        "com.hoop2work.backend.repository",
        "com.hoop2work.backend.model",
        "com.hoop2work.backend.security",
        "com.hoop2work.backend.service"}) // Add this if you have service components
public class Hoop2workProjectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hoop2workProjectBackendApplication.class, args);
    }
}
