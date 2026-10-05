package com.carelink.domain.iddocumenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;

public record IdDocumentTypeDeletedEvent(
        IdDocumentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public IdDocumentTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
