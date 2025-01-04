package org.example;

import org.example.services.implementations.UserServiceImpl;
import org.example.services.interfaces.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@EntityScan("org.example.entities")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
