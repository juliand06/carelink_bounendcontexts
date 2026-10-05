package com.carelink.application.specialistcredential.command;

import java.util.Objects;

import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record RegisterSpecialistCredentialCommand(
        AcademicProgramId academicProgramId,
        SpecialistId specialistId,
        String degreeTitle,
        String institution,
        Boolean isVerified,
        String resolutionCode,
        CountryId countryId
) {
    public RegisterSpecialistCredentialCommand {
        Objects.requireNonNull(academicProgramId, "academicProgramId must not be null");
        Objects.requireNonNull(specialistId, "specialistId must not be null");
        Objects.requireNonNull(degreeTitle, "degreeTitle must not be null");
        Objects.requireNonNull(institution, "institution must not be null");
        Objects.requireNonNull(isVerified, "isVerified must not be null");
        Objects.requireNonNull(countryId, "countryId must not be null");
    }
}
