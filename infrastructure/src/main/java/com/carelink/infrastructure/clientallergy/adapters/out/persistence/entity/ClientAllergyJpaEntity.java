package com.carelink.infrastructure.clientallergy.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client_allergies")
public class ClientAllergyJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "allergen", nullable = false, length = 200)
    private String allergen;

    @Column(name = "reaction_description", nullable = true, columnDefinition = "text")
    private String reactionDescription;

    @Column(name = "severity_level", nullable = true, length = 20)
    private String severityLevel;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @Column(name = "registered_by", nullable = false)
    private UUID registeredBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ClientAllergyJpaEntity() { }

    public ClientAllergyJpaEntity(
            UUID id,
            UUID clientId,
            String allergen,
            String reactionDescription,
            String severityLevel,
            Boolean active,
            LocalDateTime registeredAt,
            UUID registeredBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.clientId = clientId;
        this.allergen = allergen;
        this.reactionDescription = reactionDescription;
        this.severityLevel = severityLevel;
        this.active = active;
        this.registeredAt = registeredAt;
        this.registeredBy = registeredBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getClientId() { return clientId; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }
    public String getAllergen() { return allergen; }
    public void setAllergen(String allergen) { this.allergen = allergen; }
    public String getReactionDescription() { return reactionDescription; }
    public void setReactionDescription(String reactionDescription) { this.reactionDescription = reactionDescription; }
    public String getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(String severityLevel) { this.severityLevel = severityLevel; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }
    public UUID getRegisteredBy() { return registeredBy; }
    public void setRegisteredBy(UUID registeredBy) { this.registeredBy = registeredBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
