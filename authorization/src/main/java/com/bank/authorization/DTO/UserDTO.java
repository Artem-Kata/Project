package com.bank.authorization.DTO;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Role is required")
    private String role;

    @NotNull(message = "ProfileId is required")
    private Long profileId;

    @NotBlank(message = "Password is required")
    private String password;
}
