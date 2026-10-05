package com.carelink.domain.careplan.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.careplan.event.CarePlanRegisteredEvent;
import com.carelink.domain.careplan.event.CarePlanUpdatedEvent;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class CarePlan extends AggregateRoot {
    private final CarePlanId id;
    private AppointmentId appointmentId;
    private SpecialistId specialistId;
    private String planTitle;
    private String planDescription;
    private LocalDate startDate;
    private LocalDate projectedEndDate;
    private CarePlanStatusId carePlanStatusId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CarePlan(
            CarePlanId id,
            AppointmentId appointmentId,
            SpecialistId specialistId,
            String planTitle,
            String planDescription,
            LocalDate startDate,
            LocalDate projectedEndDate,
            CarePlanStatusId carePlanStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.appointmentId = Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        this.specialistId = Objects.requireNonNull(specialistId, "specialistId must not be null");
        this.planTitle = Objects.requireNonNull(planTitle, "planTitle must not be null");
        this.planDescription = planDescription;
        this.startDate = Objects.requireNonNull(startDate, "startDate must not be null");
        this.projectedEndDate = projectedEndDate;
        this.carePlanStatusId = Objects.requireNonNull(carePlanStatusId, "carePlanStatusId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static CarePlan register(
            AppointmentId appointmentId,
            SpecialistId specialistId,
            String planTitle,
            String planDescription,
            LocalDate startDate,
            LocalDate projectedEndDate,
            CarePlanStatusId carePlanStatusId) {
        CarePlanId id = CarePlanId.generate();
        LocalDateTime now = LocalDateTime.now();
        CarePlan aggregate = new CarePlan(
                id,
                appointmentId,
                specialistId,
                planTitle,
                planDescription,
                startDate,
                projectedEndDate,
                carePlanStatusId,
                now,
                now);
        aggregate.recordEvent(new CarePlanRegisteredEvent(id, now));
        return aggregate;
    }

    public static CarePlan restore(
            CarePlanId id,
            AppointmentId appointmentId,
            SpecialistId specialistId,
            String planTitle,
            String planDescription,
            LocalDate startDate,
            LocalDate projectedEndDate,
            CarePlanStatusId carePlanStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new CarePlan(
                id,
                appointmentId,
                specialistId,
                planTitle,
                planDescription,
                startDate,
                projectedEndDate,
                carePlanStatusId,
                createdAt,
                updatedAt);
    }

    public void update(
            AppointmentId appointmentId,
            SpecialistId specialistId,
            String planTitle,
            String planDescription,
            LocalDate startDate,
            LocalDate projectedEndDate,
            CarePlanStatusId carePlanStatusId) {
        this.appointmentId = Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        this.specialistId = Objects.requireNonNull(specialistId, "specialistId must not be null");
        this.planTitle = Objects.requireNonNull(planTitle, "planTitle must not be null");
        this.planDescription = planDescription;
        this.startDate = Objects.requireNonNull(startDate, "startDate must not be null");
        this.projectedEndDate = projectedEndDate;
        this.carePlanStatusId = Objects.requireNonNull(carePlanStatusId, "carePlanStatusId must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new CarePlanUpdatedEvent(
                this.id,
                this.appointmentId,
                this.specialistId,
                this.planTitle,
                this.planDescription,
                this.startDate,
                this.projectedEndDate,
                this.carePlanStatusId,
                now));
    }

    public CarePlanId id() {
        return id;
    }

    public AppointmentId appointmentId() {
        return appointmentId;
    }

    public SpecialistId specialistId() {
        return specialistId;
    }

    public String planTitle() {
        return planTitle;
    }

    public String planDescription() {
        return planDescription;
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate projectedEndDate() {
        return projectedEndDate;
    }

    public CarePlanStatusId carePlanStatusId() {
        return carePlanStatusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
