package com.study.ddd.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
public class User {
    private UUID id;
    private Username username;
    private Email email;
    private Password password;
    private Address address;
    private List<Role> roles;

    // Builder 패턴을 사용한 생성자
    @Builder
    public User(UUID id, Username username, Email email, Password password, Address address, List<Role> roles) {
        this.id = id != null ? id : UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.roles = roles != null ? roles : new ArrayList<>();
        this.roles.add(Role.USER);
    }

    public void changePassword(Password newPassword) {
        this.password = newPassword;
    }

    public void addRole(Role role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }
}
