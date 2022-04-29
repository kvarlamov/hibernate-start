package com.example.hibernatestart;

import com.example.hibernatestart.entity.Birthday;
import com.example.hibernatestart.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.*;
import static java.util.stream.Collectors.*;

@SpringBootTest
class HibernateStartApplicationTests {

    @Test
    void contextLoads() {

        User user = User.builder()
                .username("ivan@mail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .birthDate(LocalDate.of(2000, 1, 10))
                .build();

        String sql = "insert into %s (%s) values (%s)";


        var tableName = ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();

        String columnNames = Arrays.stream(fields)
                .map(field -> ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                        .collect(joining(", "));

        String values = Arrays.stream(fields)
                .map(field -> "?")
                .collect(joining(", "));

        System.out.println(sql.formatted(tableName, columnNames, values));

    }
}
