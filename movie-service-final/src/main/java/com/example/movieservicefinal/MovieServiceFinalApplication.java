package com.example.movieservicefinal;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableRabbit
@SpringBootApplication
public class MovieServiceFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceFinalApplication.class, args);
	}

}
