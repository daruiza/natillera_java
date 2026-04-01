package com.example.natillera_java.repositories.sqlserver;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.natillera_java.domain.sqlserver.User;

@Repository("jpaUserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);

    List<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
