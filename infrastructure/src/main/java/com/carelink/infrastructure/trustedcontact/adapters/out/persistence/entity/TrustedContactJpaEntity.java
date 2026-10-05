package com.carelink.infrastructure.trustedcontact.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trusted_contacts")
public class TrustedContactJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "relationship_type_id", nullable = false)
    private UUID relationshipTypeId;

    @Column(name = "is_primary_contact", nullable = false)
    private Boolean isPrimaryContact;

    @Column(name = "notes", nullable = true, columnDefinition = "text")
    private String notes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public TrustedContactJpaEntity() { }

    public TrustedContactJpaEntity(
            UUID id,
            String fullName,
            UUID relationshipTypeId,
            Boolean isPrimaryContact,
            String notes,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.relationshipTypeId = relationshipTypeId;
        this.isPrimaryContact = isPrimaryContact;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public UUID getRelationshipTypeId() { return relationshipTypeId; }
    public void setRelationshipTypeId(UUID relationshipTypeId) { this.relationshipTypeId = relationshipTypeId; }
    public Boolean getIsPrimaryContact() { return isPrimaryContact; }
    public void setIsPrimaryContact(Boolean isPrimaryContact) { this.isPrimaryContact = isPrimaryContact; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
