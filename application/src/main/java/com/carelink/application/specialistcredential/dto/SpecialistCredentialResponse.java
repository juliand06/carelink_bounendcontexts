package com.carelink.application.specialistcredential.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SpecialistCredentialResponse(
        UUID id,
        UUID academicProgramId,
        UUID specialistId,
        String degreeTitle,
        String institution,
        Boolean isVerified,
        String resolutionCode,
        UUID countryId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
