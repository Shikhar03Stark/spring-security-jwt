package com.shikhar03stark.authorization.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtApplication {

	private static void run(String[] args){
		SpringApplication.run(JwtApplication.class, args);
	}

	public static void main(String[] args) {
		run(args);
	}

}
