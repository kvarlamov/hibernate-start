package com.example.hibernatestart.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "profile", "userChats"})
@Builder
@Table(name = "users")
@Entity
@TypeDef(name = "custom1", typeClass = JsonBinaryType.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Embedded
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    //in type could be full type name or like here typeDef
    @Type(type = "custom1")
    private String info;

//    @Transient - use to exclude from mapping
    @Enumerated(EnumType.STRING)
    private Role role;

    //optional - not null - inner join, by default - left outer join
    //fetch - eager (default), lazy
    @ManyToOne(optional = false, fetch = FetchType.EAGER)//), cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Profile profile;

//    @Builder.Default
//    @ManyToMany
//    @JoinTable(
//            name = "users_chat",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns =@JoinColumn(name = "chat_id"))
//    private Set<Chat> chats = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserChat> userChats = new ArrayList<>();
}
