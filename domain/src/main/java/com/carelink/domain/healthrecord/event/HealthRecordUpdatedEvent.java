package com.carelink.domain.healthrecord.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;

public record HealthRecordUpdatedEvent(
        HealthRecordId id,
        ClientId clientId,
        String recordCode,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        HealthRecordStatusId statusId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public HealthRecordUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(clientId, "clientId must not be null");
        Objects.requireNonNull(recordCode, "recordCode must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
