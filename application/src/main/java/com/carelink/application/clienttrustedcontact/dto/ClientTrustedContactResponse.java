package com.carelink.application.clienttrustedcontact.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClientTrustedContactResponse(
        UUID id,
        UUID clientId,
        UUID trustedContactId,
        LocalDateTime createdAt
) {
}
