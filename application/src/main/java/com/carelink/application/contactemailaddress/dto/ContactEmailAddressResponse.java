package com.carelink.application.contactemailaddress.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContactEmailAddressResponse(
        UUID id,
        UUID trustedContactId,
        String emailAddress,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
