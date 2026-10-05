package com.carelink.domain.messageformat.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.messageformat.event.MessageFormatRegisteredEvent;
import com.carelink.domain.messageformat.event.MessageFormatUpdatedEvent;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;

public class MessageFormat extends AggregateRoot {
    private final MessageFormatId id;
    private String label;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private MessageFormat(
            MessageFormatId id,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static MessageFormat register(
            String label) {
        MessageFormatId id = MessageFormatId.generate();
        LocalDateTime now = LocalDateTime.now();
        MessageFormat aggregate = new MessageFormat(
                id,
                label,
                now,
                now);
        aggregate.recordEvent(new MessageFormatRegisteredEvent(id, now));
        return aggregate;
    }

    public static MessageFormat restore(
            MessageFormatId id,
            String label,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new MessageFormat(
                id,
                label,
                createdAt,
                updatedAt);
    }

    public void update(
            String label) {
        this.label = Objects.requireNonNull(label, "label must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new MessageFormatUpdatedEvent(
                this.id,
                this.label,
                now));
    }

    public MessageFormatId id() {
        return id;
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
