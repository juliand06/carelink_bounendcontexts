package com.carelink.domain.contactrelationshiptype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ContactRelationshipTypeId(UUID value) {
    public ContactRelationshipTypeId {
        Objects.requireNonNull(value, "ContactRelationshipTypeId value must not be null");
    }

    public static ContactRelationshipTypeId generate() {
        return new ContactRelationshipTypeId(UUID.randomUUID());
    }
}
