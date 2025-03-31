package com.pragma.hogar360_microservice_house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Hogar360MicroserviceHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hogar360MicroserviceHouseApplication.class, args);
	}

}
