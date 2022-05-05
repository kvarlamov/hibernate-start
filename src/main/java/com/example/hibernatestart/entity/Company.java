package com.example.hibernatestart.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(of = "name")
@Builder
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    //we could use oneToMany without @ManyToOne
    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@JoinColumn(name = "company_id") - if we have @ManytoOne in Users dont need this annotation
    //orphanRemoval - used to delete user from database if we delete from Set<user>
    @OrderBy("username ASC, personalInfo.lastname DESC") //ordering by database, for inMemory user TreeSet<>, @SortNatural and Comparable
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        user.setCompany(this);
    }
}
