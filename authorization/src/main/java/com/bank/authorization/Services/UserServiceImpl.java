package com.bank.authorization.Services;

import com.bank.authorization.DTO.UserDTO;
import com.bank.authorization.Entities.User;
import com.bank.authorization.Exceptions.ResourceNotFoundException;
import com.bank.authorization.Mapper.UserMapper;
import com.bank.authorization.Repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        log.info("User created with profileId: {}", savedUser.getProfileId());
        return UserMapper.INSTANCE.toDTO(savedUser);
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: \" + id)"));
        oldUser.setRole(userDTO.getRole());
        oldUser.setProfileId(userDTO.getProfileId());
        if (!passwordEncoder.matches(userDTO.getPassword(), oldUser.getPassword())) {
            oldUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        User updatedUser = userRepository.save(oldUser);
        log.info("User updated with id: {}", updatedUser.getId());
        return UserMapper.INSTANCE.toDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("User not found with id: \" + id)"));
        log.info("User retrieved with id: {}", id);
        return UserMapper.INSTANCE.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Retrieving all users");
        return userRepository.findAll().stream()
                .map(UserMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User deleteUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("User not found with id: \" + id)"));
        userRepository.delete(deleteUser);
        log.info("User deleted with id: {}", id);
    }
}
