package com.battercodelab.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Member extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    @Transient
    private String token;

    public Member(String id, String password, String name, MemberRole role, String token) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.token = token;
    }

}
