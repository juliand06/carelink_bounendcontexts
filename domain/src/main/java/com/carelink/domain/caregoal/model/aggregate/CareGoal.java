package com.carelink.domain.caregoal.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.caregoal.event.CareGoalRegisteredEvent;
import com.carelink.domain.caregoal.event.CareGoalUpdatedEvent;
import com.carelink.domain.caregoal.model.valueobject.CareGoalId;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.common.model.AggregateRoot;

public class CareGoal extends AggregateRoot {
    private final CareGoalId id;
    private CarePlanId carePlanId;
    private String goalDescription;
    private LocalDate targetDate;
    private LocalDateTime achievedAt;
    private String progressNotes;
    private CareGoalStatusId careGoalStatusId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CareGoal(
            CareGoalId id,
            CarePlanId carePlanId,
            String goalDescription,
            LocalDate targetDate,
            LocalDateTime achievedAt,
            String progressNotes,
            CareGoalStatusId careGoalStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.carePlanId = Objects.requireNonNull(carePlanId, "carePlanId must not be null");
        this.goalDescription = Objects.requireNonNull(goalDescription, "goalDescription must not be null");
        this.targetDate = targetDate;
        this.achievedAt = achievedAt;
        this.progressNotes = progressNotes;
        this.careGoalStatusId = Objects.requireNonNull(careGoalStatusId, "careGoalStatusId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static CareGoal register(
            CarePlanId carePlanId,
            String goalDescription,
            LocalDate targetDate,
            LocalDateTime achievedAt,
            String progressNotes,
            CareGoalStatusId careGoalStatusId) {
        CareGoalId id = CareGoalId.generate();
        LocalDateTime now = LocalDateTime.now();
        CareGoal aggregate = new CareGoal(
                id,
                carePlanId,
                goalDescription,
                targetDate,
                achievedAt,
                progressNotes,
                careGoalStatusId,
                now,
                now);
        aggregate.recordEvent(new CareGoalRegisteredEvent(id, now));
        return aggregate;
    }

    public static CareGoal restore(
            CareGoalId id,
            CarePlanId carePlanId,
            String goalDescription,
            LocalDate targetDate,
            LocalDateTime achievedAt,
            String progressNotes,
            CareGoalStatusId careGoalStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new CareGoal(
                id,
                carePlanId,
                goalDescription,
                targetDate,
                achievedAt,
                progressNotes,
                careGoalStatusId,
                createdAt,
                updatedAt);
    }

    public void update(
            CarePlanId carePlanId,
            String goalDescription,
            LocalDate targetDate,
            LocalDateTime achievedAt,
            String progressNotes,
            CareGoalStatusId careGoalStatusId) {
        this.carePlanId = Objects.requireNonNull(carePlanId, "carePlanId must not be null");
        this.goalDescription = Objects.requireNonNull(goalDescription, "goalDescription must not be null");
        this.targetDate = targetDate;
        this.achievedAt = achievedAt;
        this.progressNotes = progressNotes;
        this.careGoalStatusId = Objects.requireNonNull(careGoalStatusId, "careGoalStatusId must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new CareGoalUpdatedEvent(
                this.id,
                this.carePlanId,
                this.goalDescription,
                this.targetDate,
                this.achievedAt,
                this.progressNotes,
                this.careGoalStatusId,
                now));
    }

    public CareGoalId id() {
        return id;
    }

    public CarePlanId carePlanId() {
        return carePlanId;
    }

    public String goalDescription() {
        return goalDescription;
    }

    public LocalDate targetDate() {
        return targetDate;
    }

    public LocalDateTime achievedAt() {
        return achievedAt;
    }

    public String progressNotes() {
        return progressNotes;
    }

    public CareGoalStatusId careGoalStatusId() {
        return careGoalStatusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
