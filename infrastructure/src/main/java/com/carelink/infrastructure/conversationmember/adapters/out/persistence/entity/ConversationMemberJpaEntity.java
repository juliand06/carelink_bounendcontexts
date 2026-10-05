package com.carelink.infrastructure.conversationmember.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conversation_members")
public class ConversationMemberJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "conversation_id", nullable = false)
    private UUID conversationId;

    @Column(name = "client_id", nullable = true)
    private UUID clientId;

    @Column(name = "specialist_id", nullable = true)
    private UUID specialistId;

    @Column(name = "sender_role_id", nullable = false)
    private UUID senderRoleId;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;

    public ConversationMemberJpaEntity() { }

    public ConversationMemberJpaEntity(
            UUID id,
            UUID conversationId,
            UUID clientId,
            UUID specialistId,
            UUID senderRoleId,
            LocalDateTime joinedAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.clientId = clientId;
        this.specialistId = specialistId;
        this.senderRoleId = senderRoleId;
        this.joinedAt = joinedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getConversationId() { return conversationId; }
    public void setConversationId(UUID conversationId) { this.conversationId = conversationId; }
    public UUID getClientId() { return clientId; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }
    public UUID getSpecialistId() { return specialistId; }
    public void setSpecialistId(UUID specialistId) { this.specialistId = specialistId; }
    public UUID getSenderRoleId() { return senderRoleId; }
    public void setSenderRoleId(UUID senderRoleId) { this.senderRoleId = senderRoleId; }
    public LocalDateTime getJoinedAt() { return joinedAt; }
    public void setJoinedAt(LocalDateTime joinedAt) { this.joinedAt = joinedAt; }
}
