package com.carelink.domain.security.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.security.model.aggregate.Role;
import com.carelink.domain.security.model.valueobject.RoleId;

public interface RoleRepository {
    Role save(Role role);
    Optional<Role> findById(RoleId id);
    Optional<Role> findByName(String name);
    Optional<Role> findByAuthority(String authority);
    List<Role> findAll();
    boolean existsByAuthority(String authority);
}
