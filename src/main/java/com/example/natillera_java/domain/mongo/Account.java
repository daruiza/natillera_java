package com.example.natillera_java.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "accounts")
public class Account {
    @Id
    private String id;

    @Field("user_id")
    private String userId;

    private String bank;
    private String number;

    public Account() {
    }

    public Account(String id, String userId, String bank, String number) {
        this.id = id;
        this.userId = userId;
        this.bank = bank;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
