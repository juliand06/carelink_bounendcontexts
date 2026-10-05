package com.carelink.domain.careplan.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record CarePlanUpdatedEvent(
        CarePlanId id,
        AppointmentId appointmentId,
        SpecialistId specialistId,
        String planTitle,
        String planDescription,
        LocalDate startDate,
        LocalDate projectedEndDate,
        CarePlanStatusId carePlanStatusId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CarePlanUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        Objects.requireNonNull(specialistId, "specialistId must not be null");
        Objects.requireNonNull(planTitle, "planTitle must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(carePlanStatusId, "carePlanStatusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
