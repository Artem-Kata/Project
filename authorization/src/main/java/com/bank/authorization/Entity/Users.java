package com.bank.authorization.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table (name = "user", schema = "authorization", uniqueConstraints = @UniqueConstraint (columnNames = "password", name = "user_password_key"))
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Users {

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

    public Users(String role, Long profileId, String password) {
        this.role = role;
        this.profileId = profileId;
        this.password = password;
    }
}
