package com.example.natillera_java.controllers;

import java.util.List;
import java.util.Optional;

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

import com.example.natillera_java.domain.mongo.Account;
import com.example.natillera_java.repositories.mongo.AccountRepository;

@RestController
@RequestMapping("/api/mongo/accounts")
public class AccountMongoController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable String id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Account> getAccountsByUserId(@PathVariable String userId) {
        return accountRepository.findByUserId(userId);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable String id, @RequestBody Account accountDetails) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setUserId(accountDetails.getUserId());
            account.setBank(accountDetails.getBank());
            account.setNumber(accountDetails.getNumber());
            return ResponseEntity.ok(accountRepository.save(account));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
