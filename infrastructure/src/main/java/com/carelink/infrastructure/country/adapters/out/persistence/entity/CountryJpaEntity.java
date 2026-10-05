package com.carelink.infrastructure.country.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "countries", uniqueConstraints = {
        @UniqueConstraint(name = "uk_countries_iso_code", columnNames = "iso_code")
})
public class CountryJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "iso_code", nullable = false, length = 10)
    private String isoCode;

    @Column(name = "notes", nullable = true, length = 100)
    private String notes;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Column(name = "dial_prefix", nullable = true, length = 5)
    private String dialPrefix;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public CountryJpaEntity() { }

    public CountryJpaEntity(
            UUID id,
            String name,
            String isoCode,
            String notes,
            Boolean enabled,
            String dialPrefix,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.isoCode = isoCode;
        this.notes = notes;
        this.enabled = enabled;
        this.dialPrefix = dialPrefix;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIsoCode() { return isoCode; }
    public void setIsoCode(String isoCode) { this.isoCode = isoCode; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    public String getDialPrefix() { return dialPrefix; }
    public void setDialPrefix(String dialPrefix) { this.dialPrefix = dialPrefix; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
