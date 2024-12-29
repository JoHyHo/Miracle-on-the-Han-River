package com.study.mothr.infrastructure.persistence.user;

// infrastructure.persistence.user.UserEntity.java
import com.study.mothr.infrastructure.persistence.common.BaseEntityPersistence;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
public class UserEntity extends BaseEntityPersistence {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // 암호화된 비밀번호

    @Embedded
    private AddressEmbeddable address;

    private String phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleEmbeddable> roles = new HashSet<>();

    protected UserEntity() {}

}
