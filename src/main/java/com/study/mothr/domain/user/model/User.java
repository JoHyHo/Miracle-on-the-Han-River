package com.study.mothr.domain.user.model;

import com.study.mothr.domain.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private Address address;
    private String phoneNumber;
    private Set<Role> roles;

    public void updateAddress(Address newAddress) {
        this.address = newAddress;
        updateTimestamp();
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        updateTimestamp();
    }

    public void addRole(Role role) {
        this.roles.add(role);
        updateTimestamp();
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        updateTimestamp();
    }

}
