package com.restaurant.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the User Service application.
 *
 * This class contains the main method which is used to run the Spring Boot application.
 * It triggers the auto-configuration and component scanning to set up the application context
 * and start the embedded server.
 */
@SpringBootApplication
public class UserServiceApplication {

    /**
     * The main method which serves as the entry point for the Spring Boot application.
     * It initializes the Spring application context and starts the embedded server.
     *
     * @param args command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
