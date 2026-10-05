package com.carelink.application.trustedcontact.command;

import java.util.Objects;

import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;

public record RegisterTrustedContactCommand(
        String fullName,
        ContactRelationshipTypeId relationshipTypeId,
        Boolean isPrimaryContact,
        String notes
) {
    public RegisterTrustedContactCommand {
        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(relationshipTypeId, "relationshipTypeId must not be null");
        Objects.requireNonNull(isPrimaryContact, "isPrimaryContact must not be null");
    }
}
