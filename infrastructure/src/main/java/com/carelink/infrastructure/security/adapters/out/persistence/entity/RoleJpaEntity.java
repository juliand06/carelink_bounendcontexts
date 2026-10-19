package com.carelink.infrastructure.security.adapters.out.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "authority", nullable = false, unique = true, length = 50)
    private String authority;

    public RoleJpaEntity() {}

    public RoleJpaEntity(UUID id, String name, String authority) {
        this.id = id;
        this.name = name;
        this.authority = authority;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAuthority() { return authority; }
    public void setAuthority(String authority) { this.authority = authority; }
}
