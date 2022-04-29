package com.example.hibernatestart.entity;

import com.example.hibernatestart.entity.converter.BirthdayConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
@TypeDef(name = "custom1", typeClass = JsonBinaryType.class)
public class User {
    @Id
    private String username;
    private String firstname;
    private String lastname;

    //@Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_date")
    private LocalDate birthDate;

    //in type could be full type name or like here typeDef
    @Type(type = "custom1")
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;
}
