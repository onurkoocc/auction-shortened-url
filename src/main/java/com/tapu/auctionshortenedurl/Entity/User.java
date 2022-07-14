package com.tapu.auctionshortenedurl.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
        })
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username = "";
    private String email = "";
    private String password = "";
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(targetEntity = Url.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "urls",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "url_id")})
    private Set<Url> urls = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
