package com.carelink.infrastructure.supportmessage.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "support_messages")
public class SupportMessageJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "conversation_id", nullable = false)
    private UUID conversationId;

    @Column(name = "message_format_id", nullable = false)
    private UUID messageFormatId;

    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", nullable = false, columnDefinition = "jsonb")
    private String payload;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "extra_data", nullable = true, columnDefinition = "jsonb")
    private String extraData;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public SupportMessageJpaEntity() { }

    public SupportMessageJpaEntity(
            UUID id,
            UUID conversationId,
            UUID messageFormatId,
            UUID memberId,
            String payload,
            String extraData,
            LocalDateTime createdAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.messageFormatId = messageFormatId;
        this.memberId = memberId;
        this.payload = payload;
        this.extraData = extraData;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getConversationId() { return conversationId; }
    public void setConversationId(UUID conversationId) { this.conversationId = conversationId; }
    public UUID getMessageFormatId() { return messageFormatId; }
    public void setMessageFormatId(UUID messageFormatId) { this.messageFormatId = messageFormatId; }
    public UUID getMemberId() { return memberId; }
    public void setMemberId(UUID memberId) { this.memberId = memberId; }
    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }
    public String getExtraData() { return extraData; }
    public void setExtraData(String extraData) { this.extraData = extraData; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
