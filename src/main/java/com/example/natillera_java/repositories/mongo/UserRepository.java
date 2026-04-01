package com.example.natillera_java.repositories.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.natillera_java.domain.mongo.User;

@Repository("mongoUserRepository")
public interface UserRepository extends MongoRepository<User, String> {

    // Métodos de consulta personalizados
    List<User> findByName(String name);

    List<User> findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}
