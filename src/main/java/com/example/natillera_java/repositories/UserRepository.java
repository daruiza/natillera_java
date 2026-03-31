package com.example.natillera_java.repositories;

import com.example.natillera_java.domain.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<user, String> {
    
    // Métodos de consulta personalizados
    List<user> findByName(String name);
    
    List<user> findByEmail(String email);
    
    user findByEmailAndPassword(String email, String password);
}
