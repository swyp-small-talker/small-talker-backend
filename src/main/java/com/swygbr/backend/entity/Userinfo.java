package com.swygbr.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Userinfo")
public class Userinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    public Userinfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Userinfo() {

    }
    // getters and setters
}