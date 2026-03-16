package com.example.natillera_java;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
}
