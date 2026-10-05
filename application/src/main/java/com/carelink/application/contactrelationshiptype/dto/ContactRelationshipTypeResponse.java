package com.carelink.application.contactrelationshiptype.dto;

import java.util.UUID;

public record ContactRelationshipTypeResponse(
        UUID id,
        String label
) {
}
