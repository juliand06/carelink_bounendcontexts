package com.carelink.application.academicprogram.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AcademicProgramResponse(
        UUID id,
        String title,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
