package com.example.natillera_java.repositories.sqlserver;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.natillera_java.domain.sqlserver.Account;

@Repository("jpaAccountRepository")
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByUserId(Integer userId);
}
