package com.carelink.domain.dangerlevel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;

public record DangerLevelUpdatedEvent(
        DangerLevelId id,
        String code,
        String label,
        Boolean active,
        Integer severityRank,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DangerLevelUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(severityRank, "severityRank must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
