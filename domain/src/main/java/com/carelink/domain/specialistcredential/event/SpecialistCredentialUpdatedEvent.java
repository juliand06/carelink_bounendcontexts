package com.carelink.domain.specialistcredential.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;

public record SpecialistCredentialUpdatedEvent(
        SpecialistCredentialId id,
        AcademicProgramId academicProgramId,
        SpecialistId specialistId,
        String degreeTitle,
        String institution,
        Boolean isVerified,
        String resolutionCode,
        CountryId countryId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SpecialistCredentialUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(academicProgramId, "academicProgramId must not be null");
        Objects.requireNonNull(specialistId, "specialistId must not be null");
        Objects.requireNonNull(degreeTitle, "degreeTitle must not be null");
        Objects.requireNonNull(institution, "institution must not be null");
        Objects.requireNonNull(isVerified, "isVerified must not be null");
        Objects.requireNonNull(countryId, "countryId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
