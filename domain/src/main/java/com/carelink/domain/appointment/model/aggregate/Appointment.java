package com.carelink.domain.appointment.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.event.AppointmentRegisteredEvent;
import com.carelink.domain.appointment.event.AppointmentUpdatedEvent;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class Appointment extends AggregateRoot {
    private final AppointmentId id;
    private HealthRecordId healthRecordId;
    private SpecialistId specialistId;
    private AppointmentTypeId appointmentTypeId;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String chiefComplaint;
    private String currentCondition;
    private AppointmentModalityId modalityId;
    private AppointmentStatusId statusId;
    private final LocalDateTime createdAt;
    private final SpecialistId createdBy;
    private LocalDateTime updatedAt;
    private SpecialistId updatedBy;

    private Appointment(
            AppointmentId id,
            HealthRecordId healthRecordId,
            SpecialistId specialistId,
            AppointmentTypeId appointmentTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String chiefComplaint,
            String currentCondition,
            AppointmentModalityId modalityId,
            AppointmentStatusId statusId,
            LocalDateTime createdAt,
            SpecialistId createdBy,
            LocalDateTime updatedAt,
            SpecialistId updatedBy) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.healthRecordId = Objects.requireNonNull(healthRecordId, "healthRecordId must not be null");
        this.specialistId = Objects.requireNonNull(specialistId, "specialistId must not be null");
        this.appointmentTypeId = Objects.requireNonNull(appointmentTypeId, "appointmentTypeId must not be null");
        this.startedAt = Objects.requireNonNull(startedAt, "startedAt must not be null");
        this.endedAt = endedAt;
        this.chiefComplaint = chiefComplaint;
        this.currentCondition = currentCondition;
        this.modalityId = Objects.requireNonNull(modalityId, "modalityId must not be null");
        this.statusId = Objects.requireNonNull(statusId, "statusId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
        this.updatedBy = updatedBy;
    }

    public static Appointment register(
            HealthRecordId healthRecordId,
            SpecialistId specialistId,
            AppointmentTypeId appointmentTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String chiefComplaint,
            String currentCondition,
            AppointmentModalityId modalityId,
            AppointmentStatusId statusId,
            SpecialistId createdBy,
            SpecialistId updatedBy) {
        AppointmentId id = AppointmentId.generate();
        LocalDateTime now = LocalDateTime.now();
        Appointment aggregate = new Appointment(
                id,
                healthRecordId,
                specialistId,
                appointmentTypeId,
                startedAt,
                endedAt,
                chiefComplaint,
                currentCondition,
                modalityId,
                statusId,
                now,
                createdBy,
                now,
                updatedBy);
        aggregate.recordEvent(new AppointmentRegisteredEvent(id, now));
        return aggregate;
    }

    public static Appointment restore(
            AppointmentId id,
            HealthRecordId healthRecordId,
            SpecialistId specialistId,
            AppointmentTypeId appointmentTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String chiefComplaint,
            String currentCondition,
            AppointmentModalityId modalityId,
            AppointmentStatusId statusId,
            LocalDateTime createdAt,
            SpecialistId createdBy,
            LocalDateTime updatedAt,
            SpecialistId updatedBy) {
        return new Appointment(
                id,
                healthRecordId,
                specialistId,
                appointmentTypeId,
                startedAt,
                endedAt,
                chiefComplaint,
                currentCondition,
                modalityId,
                statusId,
                createdAt,
                createdBy,
                updatedAt,
                updatedBy);
    }

    public void update(
            HealthRecordId healthRecordId,
            SpecialistId specialistId,
            AppointmentTypeId appointmentTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String chiefComplaint,
            String currentCondition,
            AppointmentModalityId modalityId,
            AppointmentStatusId statusId,
            SpecialistId updatedBy) {
        this.healthRecordId = Objects.requireNonNull(healthRecordId, "healthRecordId must not be null");
        this.specialistId = Objects.requireNonNull(specialistId, "specialistId must not be null");
        this.appointmentTypeId = Objects.requireNonNull(appointmentTypeId, "appointmentTypeId must not be null");
        this.startedAt = Objects.requireNonNull(startedAt, "startedAt must not be null");
        this.endedAt = endedAt;
        this.chiefComplaint = chiefComplaint;
        this.currentCondition = currentCondition;
        this.modalityId = Objects.requireNonNull(modalityId, "modalityId must not be null");
        this.statusId = Objects.requireNonNull(statusId, "statusId must not be null");
        this.updatedBy = updatedBy;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new AppointmentUpdatedEvent(
                this.id,
                this.healthRecordId,
                this.specialistId,
                this.appointmentTypeId,
                this.startedAt,
                this.endedAt,
                this.chiefComplaint,
                this.currentCondition,
                this.modalityId,
                this.statusId,
                this.updatedBy,
                now));
    }

    public AppointmentId id() {
        return id;
    }

    public HealthRecordId healthRecordId() {
        return healthRecordId;
    }

    public SpecialistId specialistId() {
        return specialistId;
    }

    public AppointmentTypeId appointmentTypeId() {
        return appointmentTypeId;
    }

    public LocalDateTime startedAt() {
        return startedAt;
    }

    public LocalDateTime endedAt() {
        return endedAt;
    }

    public String chiefComplaint() {
        return chiefComplaint;
    }

    public String currentCondition() {
        return currentCondition;
    }

    public AppointmentModalityId modalityId() {
        return modalityId;
    }

    public AppointmentStatusId statusId() {
        return statusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public SpecialistId createdBy() {
        return createdBy;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public SpecialistId updatedBy() {
        return updatedBy;
    }
}
