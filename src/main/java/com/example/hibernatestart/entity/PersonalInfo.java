package com.example.hibernatestart.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable //встраиваемый компонент
public class PersonalInfo {
    private String firstname;
    private String lastname;

    //@Convert(converter = BirthdayConverter.class)
    //@Column(name = "birth_date")
    private LocalDate birthDate;
}
