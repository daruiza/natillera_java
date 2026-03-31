package com.example.natillera_java.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
 
    @GetMapping({"/hello", "/hi"})
    public String holaMundo() {
        System.out.println("¡Hola Mundo con Spring Boot!");
        return "¡Hola Mundo con Spring Boot!";
    }


    @GetMapping({"/saludo/{nombre}"})
    public String saludo(@PathVariable String nombre) {
        System.out.println("¡Hola " + nombre + " con Spring Boot!");
        return "¡Hola " + nombre + " con Spring Boot!";
    }

}