package com.carelink.application.contactphonenumber.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContactPhoneNumberResponse(
        UUID id,
        UUID trustedContactId,
        String phoneNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
