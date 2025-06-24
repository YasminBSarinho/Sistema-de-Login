package com.ifpb.sistemaLogin.sistema.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SistemaLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaLoginApplication.class, args);
	}

}
