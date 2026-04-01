package com.example.natillera_java.repositories.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.natillera_java.domain.mongo.Account;

@Repository("mongoAccountRepository")
public interface AccountRepository extends MongoRepository<Account, String> {

    List<Account> findByUserId(String userId);
}
