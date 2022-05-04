package com.example.hibernatestart.dao;


import com.example.hibernatestart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String username);
    boolean existsByUsername(String username);
}
