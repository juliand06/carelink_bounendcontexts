package com.carelink.application.trustedcontact.command;

import java.util.Objects;

import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record UpdateTrustedContactCommand(
        TrustedContactId id,
        String fullName,
        ContactRelationshipTypeId relationshipTypeId,
        Boolean isPrimaryContact,
        String notes
) {
    public UpdateTrustedContactCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(relationshipTypeId, "relationshipTypeId must not be null");
        Objects.requireNonNull(isPrimaryContact, "isPrimaryContact must not be null");
    }
}
