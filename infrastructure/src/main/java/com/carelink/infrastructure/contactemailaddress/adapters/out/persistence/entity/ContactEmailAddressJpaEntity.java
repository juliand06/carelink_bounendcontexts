package com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact_email_addresses")
public class ContactEmailAddressJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "trusted_contact_id", nullable = false)
    private UUID trustedContactId;

    @Column(name = "email_address", nullable = false, length = 150)
    private String emailAddress;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ContactEmailAddressJpaEntity() { }

    public ContactEmailAddressJpaEntity(
            UUID id,
            UUID trustedContactId,
            String emailAddress,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.trustedContactId = trustedContactId;
        this.emailAddress = emailAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTrustedContactId() { return trustedContactId; }
    public void setTrustedContactId(UUID trustedContactId) { this.trustedContactId = trustedContactId; }
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
