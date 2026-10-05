package com.carelink.domain.iddocumenttype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record IdDocumentTypeId(UUID value) {
    public IdDocumentTypeId {
        Objects.requireNonNull(value, "IdDocumentTypeId value must not be null");
    }

    public static IdDocumentTypeId generate() {
        return new IdDocumentTypeId(UUID.randomUUID());
    }
}
