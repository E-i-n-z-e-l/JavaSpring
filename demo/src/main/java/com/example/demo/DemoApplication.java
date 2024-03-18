package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Аннотация @SpringBootApplication объединяет несколько аннотаций, таких как
@Configuration, @EnableAutoConfiguration и @ComponentScan, что делает класс
основным классом Spring Boot приложения. */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//  http://localhost:8080/users
}
