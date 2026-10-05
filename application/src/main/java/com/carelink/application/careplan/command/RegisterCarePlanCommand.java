package com.carelink.application.careplan.command;

import java.time.LocalDate;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record RegisterCarePlanCommand(
        AppointmentId appointmentId,
        SpecialistId specialistId,
        String planTitle,
        String planDescription,
        LocalDate startDate,
        LocalDate projectedEndDate,
        CarePlanStatusId carePlanStatusId
) {
    public RegisterCarePlanCommand {
        Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        Objects.requireNonNull(specialistId, "specialistId must not be null");
        Objects.requireNonNull(planTitle, "planTitle must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(carePlanStatusId, "carePlanStatusId must not be null");
    }
}
