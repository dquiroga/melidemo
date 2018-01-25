package com.mercadolibre.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCircuitBreaker
@ComponentScan("com.mercadolibre")
public class MeLiGatewahApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeLiGatewahApplication.class, args);
	}

}
