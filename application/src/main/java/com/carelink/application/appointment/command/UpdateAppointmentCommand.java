package com.carelink.application.appointment.command;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record UpdateAppointmentCommand(
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
        SpecialistId updatedBy
) {
    public UpdateAppointmentCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(healthRecordId, "healthRecordId must not be null");
        Objects.requireNonNull(specialistId, "specialistId must not be null");
        Objects.requireNonNull(appointmentTypeId, "appointmentTypeId must not be null");
        Objects.requireNonNull(startedAt, "startedAt must not be null");
        Objects.requireNonNull(modalityId, "modalityId must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
    }
}
