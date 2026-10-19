package com.carelink.domain.security.port.repository;

import java.util.Optional;

import com.carelink.domain.security.model.aggregate.User;
import com.carelink.domain.security.model.valueobject.UserId;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UserId id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    void delete(User user);
}
