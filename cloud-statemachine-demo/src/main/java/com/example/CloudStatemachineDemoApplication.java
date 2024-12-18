package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableWithStateMachine;

@SpringBootApplication
public class CloudStatemachineDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStatemachineDemoApplication.class, args);
    }

}
