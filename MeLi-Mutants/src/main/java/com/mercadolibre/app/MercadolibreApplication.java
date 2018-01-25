package com.mercadolibre.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages={"com.mercadolibre.app"})
@ComponentScan("com.mercadolibre")
@EntityScan("com.mercadolibre.domain")
@EnableMongoRepositories("com.mercadolibre.repository")
public class MercadolibreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadolibreApplication.class, args);
	}
}
