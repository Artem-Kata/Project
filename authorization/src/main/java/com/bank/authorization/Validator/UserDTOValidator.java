package com.bank.authorization.Validator;


import com.bank.authorization.DTO.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        if (userDTO.getRole() == null || userDTO.getRole().isEmpty()) {
            errors.rejectValue("role", "role.empty", "Role не должен быть пустым");
        }

        if (userDTO.getProfileId() == null) {
            errors.rejectValue("profileId", "profileId.null", "Profile ID не должен быть пустым");
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            errors.rejectValue("password", "password.empty", "Password не должен быть пустым");
        }
    }
}
