package com.carelink.application.specialist.command;

import java.util.Objects;

import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;

public record UpdateSpecialistCommand(
        SpecialistId id,
        IdDocumentTypeId idDocumentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        SpecialistRoleId specialistRoleId,
        String professionalLicense,
        Boolean active,
        MunicipalityId municipalityId
) {
    public UpdateSpecialistCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(idDocumentTypeId, "idDocumentTypeId must not be null");
        Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
        Objects.requireNonNull(specialistRoleId, "specialistRoleId must not be null");
        Objects.requireNonNull(professionalLicense, "professionalLicense must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(municipalityId, "municipalityId must not be null");
    }
}
