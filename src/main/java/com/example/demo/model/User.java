package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> projects;

    // Manually added Getter for Password
    public String getPassword() {
        return this.password;
    }

    // Manually added Setter for Password
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Standard Getters/Setters for Email
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getId() { return id; }
}
