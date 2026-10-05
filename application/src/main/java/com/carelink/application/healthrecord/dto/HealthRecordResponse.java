package com.carelink.application.healthrecord.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record HealthRecordResponse(
        UUID id,
        UUID clientId,
        LocalDateTime openingDate,
        String recordCode,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        UUID statusId,
        LocalDateTime createdAt,
        UUID createdBy
) {
}
