package br.com.microservices.microservice.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class microserviceOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(microserviceOrderApplication.class, args);
	}

}
