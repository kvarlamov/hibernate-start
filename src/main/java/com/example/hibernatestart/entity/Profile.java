package com.example.hibernatestart.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    //@PrimaryKeyJoinColumn
    private User user;

    private String street;

    @Column(columnDefinition = "bpchar")
    private String language;

    public void setUser(User user) {
        user.setProfile(this);
        this.user = user;
    }
}
