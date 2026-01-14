package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You MUST add this line. 
    // It tells Spring to generate: SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);
}