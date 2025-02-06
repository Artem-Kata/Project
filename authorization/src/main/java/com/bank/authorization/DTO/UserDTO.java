package com.bank.authorization.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String role;
    private Long profileId;
    private String password;

    public UserDTO(String role, Long profileId, String password) {
        this.role = role;
        this.profileId = profileId;
        this.password = password;
    }
}
