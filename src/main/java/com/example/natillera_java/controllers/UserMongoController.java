package com.example.natillera_java.controllers;

import com.example.natillera_java.domain.user;
import com.example.natillera_java.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo/users")
public class UserMongoController {

    @Autowired
    private UserRepository userRepository;

    // Obtener todos los usuarios
    @GetMapping
    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<user> getUserById(@PathVariable String id) {
        Optional<user> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo usuario
    @PostMapping
    public user createUser(@RequestBody user user) {
        return userRepository.save(user);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<user> updateUser(@PathVariable String id, @RequestBody user userDetails) {
        Optional<user> optionalUser = userRepository.findById(id);
        
        if (optionalUser.isPresent()) {
            user user = optionalUser.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            return ResponseEntity.ok(userRepository.save(user));
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Buscar por nombre
    @GetMapping("/search/name/{name}")
    public List<user> getUsersByName(@PathVariable String name) {
        return userRepository.findByName(name);
    }

    // Buscar por email
    @GetMapping("/search/email/{email}")
    public List<user> getUsersByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }
}
