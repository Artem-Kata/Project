package com.bank.authorization.Service;

import com.bank.authorization.DTO.UserDTO;
import com.bank.authorization.Entity.Users;
import com.bank.authorization.Exception.AuthorizationException;
import com.bank.authorization.Mapper.UserMapper;
import com.bank.authorization.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper.INSTANCE::toDTO)
                .orElseThrow (() -> new AuthorizationException("пользователь c id " + id + " не найден"));
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        Users user = UserMapper.INSTANCE.toEntity(userDTO);
        Users savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.toDTO(savedUser);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setRole(userDTO.getRole());
                    user.setProfileId(userDTO.getProfileId());
                    user.setPassword(userDTO.getPassword());
                    Users updatedUser = userRepository.save(user);
                    return UserMapper.INSTANCE.toDTO(updatedUser);
                })
                .orElseThrow(() -> new AuthorizationException("пользователя с id " + id + "обновить не получилось"));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
