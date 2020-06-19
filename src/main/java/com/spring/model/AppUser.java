package com.spring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String pass;

    @ManyToOne
    private AppRole role;


}
