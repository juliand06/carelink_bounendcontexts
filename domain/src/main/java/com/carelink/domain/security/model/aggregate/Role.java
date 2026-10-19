package com.carelink.domain.security.model.aggregate;

import java.util.Objects;

import com.carelink.domain.security.exception.DomainSecurityException;
import com.carelink.domain.security.model.valueobject.RoleId;

public class Role {
    private final RoleId id;
    private final String name;
    private final String authority;

    private Role(RoleId id, String name, String authority) {
        this.id = Objects.requireNonNull(id, "RoleId must not be null");
        this.name = Objects.requireNonNull(name, "Role name must not be null");
        this.authority = Objects.requireNonNull(authority, "Authority must not be null");
    }

    public static Role create(String name, String authority) {
        if (name == null || name.isBlank()) {
            throw new DomainSecurityException("El nombre del rol no puede estar vacío");
        }
        if (authority == null || authority.isBlank()) {
            throw new DomainSecurityException("El authority del rol no puede estar vacío");
        }
        return new Role(RoleId.generate(), name.toUpperCase().trim(), authority.toUpperCase().trim());
    }

    public static Role restore(RoleId id, String name, String authority) {
        return new Role(id, name, authority);
    }

    public boolean hasAuthority(String authority) {
        return this.authority.equalsIgnoreCase(authority);
    }

    public RoleId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String authority() {
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
