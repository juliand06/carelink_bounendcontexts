package com.carelink.domain.supportconversationstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.supportconversationstatus.event.SupportConversationStatusRegisteredEvent;
import com.carelink.domain.supportconversationstatus.event.SupportConversationStatusUpdatedEvent;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;

public class SupportConversationStatus extends AggregateRoot {
    private final SupportConversationStatusId id;
    private String code;
    private String label;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SupportConversationStatus(
            SupportConversationStatusId id,
            String code,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static SupportConversationStatus register(
            String code,
            String label) {
        SupportConversationStatusId id = SupportConversationStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        SupportConversationStatus aggregate = new SupportConversationStatus(
                id,
                code,
                label,
                now,
                now);
        aggregate.recordEvent(new SupportConversationStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static SupportConversationStatus restore(
            SupportConversationStatusId id,
            String code,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new SupportConversationStatus(
                id,
                code,
                label,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String label) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new SupportConversationStatusUpdatedEvent(
                this.id,
                this.code,
                this.label,
                now));
    }

    public SupportConversationStatusId id() {
        return id;
    }

    public String code() {
        return code;
    }

    public String label() {
        return label;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
