package com.carelink.application.client.command;

import java.time.LocalDate;
import java.util.Objects;

import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record RegisterClientCommand(
        IdDocumentTypeId idDocumentTypeId,
        String documentNumber,
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        LocalDate birthDate,
        BiologicalSexId biologicalSexId,
        BiologicalSexId genderIdentityId,
        String email,
        String mobilePhone,
        String homeAddress,
        Boolean active,
        SpecialistId createdBy,
        SpecialistId updatedBy,
        MunicipalityId municipalityId
) {
    public RegisterClientCommand {
        Objects.requireNonNull(idDocumentTypeId, "idDocumentTypeId must not be null");
        Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
        Objects.requireNonNull(birthDate, "birthDate must not be null");
        Objects.requireNonNull(biologicalSexId, "biologicalSexId must not be null");
        Objects.requireNonNull(genderIdentityId, "genderIdentityId must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(municipalityId, "municipalityId must not be null");
    }
}
