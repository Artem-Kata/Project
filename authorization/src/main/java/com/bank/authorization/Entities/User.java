package com.bank.authorization.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table (name = "user", schema = "authorization", uniqueConstraints = @UniqueConstraint (columnNames = "password", name = "user_password_key"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column (nullable = false, length = 40)
    private String role;

    @Column (name = "profile_id", nullable = false)
    private Long profileId;

    @Column (name = "password", nullable = false, length = 500)
    private String password;

    public User (String role, Long profileId, String password) {
        this.role = role;
        this.profileId = profileId;
        this.password = password;
    }
}
