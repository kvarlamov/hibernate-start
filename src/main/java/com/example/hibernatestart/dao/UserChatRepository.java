package com.example.hibernatestart.dao;

import com.example.hibernatestart.entity.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRepository extends JpaRepository<UserChat, Long> {
}
