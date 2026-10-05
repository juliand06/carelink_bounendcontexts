package com.carelink.domain.healthrecord.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.healthrecord.event.HealthRecordRegisteredEvent;
import com.carelink.domain.healthrecord.event.HealthRecordUpdatedEvent;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class HealthRecord extends AggregateRoot {
    private final HealthRecordId id;
    private ClientId clientId;
    private final LocalDateTime openingDate;
    private String recordCode;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private HealthRecordStatusId statusId;
    private final LocalDateTime createdAt;
    private final SpecialistId createdBy;

    private HealthRecord(
            HealthRecordId id,
            ClientId clientId,
            LocalDateTime openingDate,
            String recordCode,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            HealthRecordStatusId statusId,
            LocalDateTime createdAt,
            SpecialistId createdBy) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.clientId = Objects.requireNonNull(clientId, "clientId must not be null");
        this.openingDate = Objects.requireNonNull(openingDate, "openingDate must not be null");
        this.recordCode = Objects.requireNonNull(recordCode, "recordCode must not be null");
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.statusId = Objects.requireNonNull(statusId, "statusId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
    }

    public static HealthRecord register(
            ClientId clientId,
            String recordCode,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            HealthRecordStatusId statusId,
            SpecialistId createdBy) {
        HealthRecordId id = HealthRecordId.generate();
        LocalDateTime now = LocalDateTime.now();
        HealthRecord aggregate = new HealthRecord(
                id,
                clientId,
                now,
                recordCode,
                openedAt,
                closedAt,
                statusId,
                now,
                createdBy);
        aggregate.recordEvent(new HealthRecordRegisteredEvent(id, now));
        return aggregate;
    }

    public static HealthRecord restore(
            HealthRecordId id,
            ClientId clientId,
            LocalDateTime openingDate,
            String recordCode,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            HealthRecordStatusId statusId,
            LocalDateTime createdAt,
            SpecialistId createdBy) {
        return new HealthRecord(
                id,
                clientId,
                openingDate,
                recordCode,
                openedAt,
                closedAt,
                statusId,
                createdAt,
                createdBy);
    }

    public void update(
            ClientId clientId,
            String recordCode,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            HealthRecordStatusId statusId) {
        this.clientId = Objects.requireNonNull(clientId, "clientId must not be null");
        this.recordCode = Objects.requireNonNull(recordCode, "recordCode must not be null");
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.statusId = Objects.requireNonNull(statusId, "statusId must not be null");
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new HealthRecordUpdatedEvent(
                this.id,
                this.clientId,
                this.recordCode,
                this.openedAt,
                this.closedAt,
                this.statusId,
                now));
    }

    public HealthRecordId id() {
        return id;
    }

    public ClientId clientId() {
        return clientId;
    }

    public LocalDateTime openingDate() {
        return openingDate;
    }

    public String recordCode() {
        return recordCode;
    }

    public LocalDateTime openedAt() {
        return openedAt;
    }

    public LocalDateTime closedAt() {
        return closedAt;
    }

    public HealthRecordStatusId statusId() {
        return statusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public SpecialistId createdBy() {
        return createdBy;
    }
}
