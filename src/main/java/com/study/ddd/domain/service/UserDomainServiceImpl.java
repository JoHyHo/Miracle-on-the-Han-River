package com.study.ddd.domain.service;


import com.study.ddd.domain.model.Role;
import com.study.ddd.domain.model.User;
import com.study.ddd.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;

    public UserDomainServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void registerNewUser(User user) {
        if (userRepository.findByUsername(user.getUsername().getValue()).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void assignRole(User user, Role role) {
        user.addRole(role);
        userRepository.save(user);
    }
}
