package com.restaurant.RestaurantMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The entry point for the Restaurant Microservice Spring Boot application.
 * This class is responsible for starting the application and enabling Feign clients for inter-service communication.
 */
@SpringBootApplication
@EnableFeignClients
public class RestaurantMicroserviceApplication {

	/**
	 * The main method that serves as the entry point for the application.
	 * It launches the Spring Boot application by invoking the {@link SpringApplication#run} method.
	 *
	 * @param args command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestaurantMicroserviceApplication.class, args);
	}
}
