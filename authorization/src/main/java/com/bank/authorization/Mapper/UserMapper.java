package com.bank.authorization.Mapper;

import com.bank.authorization.DTO.UserDTO;
import com.bank.authorization.Entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(Users user);

    Users toEntity(UserDTO userDTO);
}
