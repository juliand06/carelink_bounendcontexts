package com.carelink.domain.handoffassignment.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffassignment.event.HandoffAssignmentRegisteredEvent;
import com.carelink.domain.handoffassignment.event.HandoffAssignmentUpdatedEvent;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class HandoffAssignment extends AggregateRoot {
    private final HandoffAssignmentId id;
    private HandoffId handoffId;
    private SpecialistId specialistId;
    private final LocalDateTime assignedAt;

    private HandoffAssignment(
            HandoffAssignmentId id,
            HandoffId handoffId,
            SpecialistId specialistId,
            LocalDateTime assignedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.handoffId = Objects.requireNonNull(handoffId, "handoffId must not be null");
        this.specialistId = Objects.requireNonNull(specialistId, "specialistId must not be null");
        this.assignedAt = Objects.requireNonNull(assignedAt, "assignedAt must not be null");
    }

    public static HandoffAssignment register(
            HandoffId handoffId,
            SpecialistId specialistId) {
        HandoffAssignmentId id = HandoffAssignmentId.generate();
        LocalDateTime now = LocalDateTime.now();
        HandoffAssignment aggregate = new HandoffAssignment(
                id,
                handoffId,
                specialistId,
                now);
        aggregate.recordEvent(new HandoffAssignmentRegisteredEvent(id, now));
        return aggregate;
    }

    public static HandoffAssignment restore(
            HandoffAssignmentId id,
            HandoffId handoffId,
            SpecialistId specialistId,
            LocalDateTime assignedAt) {
        return new HandoffAssignment(
                id,
                handoffId,
                specialistId,
                assignedAt);
    }

    public void update(
            HandoffId handoffId,
            SpecialistId specialistId) {
        this.handoffId = Objects.requireNonNull(handoffId, "handoffId must not be null");
        this.specialistId = Objects.requireNonNull(specialistId, "specialistId must not be null");
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new HandoffAssignmentUpdatedEvent(
                this.id,
                this.handoffId,
                this.specialistId,
                now));
    }

    public HandoffAssignmentId id() {
        return id;
    }

    public HandoffId handoffId() {
        return handoffId;
    }

    public SpecialistId specialistId() {
        return specialistId;
    }

    public LocalDateTime assignedAt() {
        return assignedAt;
    }
}
