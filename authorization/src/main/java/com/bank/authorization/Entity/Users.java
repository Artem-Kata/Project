package com.bank.authorization.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "user", uniqueConstraints = @UniqueConstraint (columnNames = "password"))
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String role;

    @Column (name = "profile_id")
    private Long profileId;

    @Column (name = "password", unique = true)
    private String password;

    public Users(String role, Long profileId, String password) {
        this.role = role;
        this.profileId = profileId;
        this.password = password;
    }
}
