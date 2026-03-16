package com.example.natillera_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NatilleraJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NatilleraJavaApplication.class, args);
	}

	@GetMapping("/hola")
    public String holaMundo() {
        return "¡Hola Mundo con Spring Boot!";
    }

}
