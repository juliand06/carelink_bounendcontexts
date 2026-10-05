package com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "client_trusted_contacts", uniqueConstraints = {
        @UniqueConstraint(name = "uk_client_trusted_contacts_client_id_trusted_contact_id", columnNames = {"client_id", "trusted_contact_id"})
})
public class ClientTrustedContactJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "trusted_contact_id", nullable = false)
    private UUID trustedContactId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public ClientTrustedContactJpaEntity() { }

    public ClientTrustedContactJpaEntity(
            UUID id,
            UUID clientId,
            UUID trustedContactId,
            LocalDateTime createdAt) {
        this.id = id;
        this.clientId = clientId;
        this.trustedContactId = trustedContactId;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getClientId() { return clientId; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }
    public UUID getTrustedContactId() { return trustedContactId; }
    public void setTrustedContactId(UUID trustedContactId) { this.trustedContactId = trustedContactId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
