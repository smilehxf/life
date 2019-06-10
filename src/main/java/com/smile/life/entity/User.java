package com.smile.life.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String username;
    private String password;
    private String role;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

