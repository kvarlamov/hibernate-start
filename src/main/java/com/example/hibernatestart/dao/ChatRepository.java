package com.example.hibernatestart.dao;

import com.example.hibernatestart.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
