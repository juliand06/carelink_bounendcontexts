package com.carelink.application.healthrecord.command;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record RegisterHealthRecordCommand(
        ClientId clientId,
        String recordCode,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        HealthRecordStatusId statusId,
        SpecialistId createdBy
) {
    public RegisterHealthRecordCommand {
        Objects.requireNonNull(clientId, "clientId must not be null");
        Objects.requireNonNull(recordCode, "recordCode must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(createdBy, "createdBy must not be null");
    }
}
