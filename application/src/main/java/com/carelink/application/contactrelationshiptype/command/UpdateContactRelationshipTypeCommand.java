package com.carelink.application.contactrelationshiptype.command;

import java.util.Objects;

import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;

public record UpdateContactRelationshipTypeCommand(
        ContactRelationshipTypeId id,
        String label
) {
    public UpdateContactRelationshipTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(label, "label must not be null");
    }
}
