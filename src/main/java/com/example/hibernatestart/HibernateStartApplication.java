package com.example.hibernatestart;

import com.example.hibernatestart.dao.UserRepository;
import com.example.hibernatestart.entity.Birthday;
import com.example.hibernatestart.entity.Role;
import com.example.hibernatestart.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

@SpringBootApplication
@Slf4j
public class HibernateStartApplication {

    //private static final Logger log = LoggerFactory.getLogger(HibernateStartApplication.class);

    public static void main(String[] args) {
        try {
            var context = SpringApplication.run(HibernateStartApplication.class, args);

            var repo = context.getBean(UserRepository.class);
            User user = User.builder()
                    .username("ivan2@mail.com")
                    .firstname("Ivan2.0")
                    .lastname("Ivanov2.0")
                    .birthDate(LocalDate.of(2000, 1, 10))
                    .role(Role.ADMIN)
                    .info("""
                        {
                        "name": "Ivan2.0",
                        "id": 25
                        }
                        """)
                    .build();
            log.trace("User entity created, object: {}", user);
            repo.saveAndFlush(user);
            log.info("User entity saved to database, object: {}", user);
        } catch (Exception ex) {
            log.error("Exception!!! {}, \nStacktrace: {}", ex.getMessage(), ex.getStackTrace());
        }

    }
}
