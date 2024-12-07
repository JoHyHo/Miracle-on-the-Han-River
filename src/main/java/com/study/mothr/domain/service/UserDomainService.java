package com.study.ddd.domain.service;

import com.study.ddd.domain.model.Role;
import com.study.ddd.domain.model.User;

public interface UserDomainService {
    void registerNewUser(User user);
    void assignRole(User user, Role role);
}