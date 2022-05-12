package com.cursoUdemy.apiJunit5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class ApiJunit5Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiJunit5Application.class, args);
	}

}
