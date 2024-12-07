package com.study.ddd.application.service;

import com.study.ddd.application.dto.UserDTO;
import com.study.ddd.application.mapper.UserMapper;
import com.study.ddd.domain.model.*;
import com.study.ddd.domain.repository.UserRepository;
import com.study.ddd.domain.service.UserDomainService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserAppService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDomainService userDomainService;

    public UserAppService(UserRepository userRepository, UserMapper userMapper,
                          PasswordEncoder passwordEncoder, UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDomainService = userDomainService;
    }

    @Transactional
    public UserDTO registerUser(UserDTO userDTO, String plainPassword) {
        Password password = Password.builder()
                .value(passwordEncoder.encode(plainPassword))
                .build();

        User user = User.builder()
                .username(new Username(userDTO.getUsername()))
                .email(new Email(userDTO.getEmail()))
                .password(password)
                .address(parseAddress(userDTO.getAddress()))
                .build();

        // 도메인 서비스 호출
        userDomainService.registerNewUser(user);

        return userMapper.toDTO(user);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO updateUser(UUID userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        user = User.builder()
                .id(user.getId())
                .username(new Username(userDTO.getUsername()))
                .email(new Email(userDTO.getEmail()))
                .password(user.getPassword()) // 기존 패스워드 유지
                .address(parseAddress(userDTO.getAddress()))
                .roles(user.getRoles()) // 기존 역할 유지
                .build();

        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        userRepository.delete(user);
    }

    @Transactional
    public UserDTO changeUserPassword(UUID userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        Password password = Password.builder()
                .value(passwordEncoder.encode(newPassword))
                .build();
        user.changePassword(password);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    private Address parseAddress(String addressStr) {
        String[] parts = addressStr.split(", ");
        if(parts.length != 4){
            throw new IllegalArgumentException("Address must include street, city, state, and zip code.");
        }
        return Address.builder()
                .street(parts[0])
                .city(parts[1])
                .state(parts[2])
                .zipCode(parts[3])
                .build();
    }
}
