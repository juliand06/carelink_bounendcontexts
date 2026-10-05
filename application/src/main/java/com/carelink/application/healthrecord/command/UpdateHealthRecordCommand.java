package com.carelink.application.healthrecord.command;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;

public record UpdateHealthRecordCommand(
        HealthRecordId id,
        ClientId clientId,
        String recordCode,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        HealthRecordStatusId statusId
) {
    public UpdateHealthRecordCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(clientId, "clientId must not be null");
        Objects.requireNonNull(recordCode, "recordCode must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
    }
}
