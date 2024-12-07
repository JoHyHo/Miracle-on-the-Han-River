package com.study.ddd.infrastructure.persistence;

import com.study.ddd.application.mapper.UserMapper;
import com.study.ddd.domain.model.User;
import com.study.ddd.domain.repository.UserRepository;
import com.study.ddd.infrastructure.persistence.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserMapper userMapper;

    public JpaUserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        if (user.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
        return userMapper.toDomain(entity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        UserEntity entity = entityManager.find(UserEntity.class, id);
        return Optional.ofNullable(userMapper.toDomain(entity));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String jpql = "SELECT u FROM UserEntity u WHERE u.username = :username";
        try {
            UserEntity entity = entityManager.createQuery(jpql, UserEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(userMapper.toDomain(entity));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(User user) {
        UserEntity entity = userMapper.toEntity(user);
        entityManager.remove(entity);
    }
}
