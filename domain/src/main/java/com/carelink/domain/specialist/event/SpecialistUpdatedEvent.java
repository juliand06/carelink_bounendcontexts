package com.carelink.domain.specialist.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;

public record SpecialistUpdatedEvent(
        SpecialistId id,
        IdDocumentTypeId idDocumentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        SpecialistRoleId specialistRoleId,
        String professionalLicense,
        Boolean active,
        MunicipalityId municipalityId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SpecialistUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(idDocumentTypeId, "idDocumentTypeId must not be null");
        Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
        Objects.requireNonNull(specialistRoleId, "specialistRoleId must not be null");
        Objects.requireNonNull(professionalLicense, "professionalLicense must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(municipalityId, "municipalityId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
