package com.carelink.application.specialist.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SpecialistResponse(
        UUID id,
        UUID idDocumentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        UUID specialistRoleId,
        String professionalLicense,
        Boolean active,
        UUID municipalityId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
