package com.example.natillera_java.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.natillera_java.domain.sqlserver.User;
import com.example.natillera_java.repositories.sqlserver.AccountRepository;
import com.example.natillera_java.repositories.sqlserver.UserRepository;

@RestController
@RequestMapping("/api/sqlserver/users")
public class UserSqlServerController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Map<String, Object> buildUserResponse(User user) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("accounts", accountRepository.findByUserId(user.getId()));
        return response;
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<Map<String, Object>> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> ResponseEntity.ok(buildUserResponse(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo usuario
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            return ResponseEntity.ok(userRepository.save(user));
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Buscar por nombre
    @GetMapping("/search/name/{name}")
    public List<Map<String, Object>> getUsersByName(@PathVariable String name) {
        return userRepository.findByName(name).stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }

    // Buscar por email
    @GetMapping("/search/email/{email}")
    public List<Map<String, Object>> getUsersByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email).stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }
}
