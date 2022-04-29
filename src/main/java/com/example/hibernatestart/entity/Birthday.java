package com.example.hibernatestart.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Birthday(LocalDate birthday) {
    public long getAge() {
        return ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }
}
