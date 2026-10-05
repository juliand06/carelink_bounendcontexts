package com.carelink.domain.municipality.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record MunicipalityId(UUID value) {
    public MunicipalityId {
        Objects.requireNonNull(value, "MunicipalityId value must not be null");
    }

    public static MunicipalityId generate() {
        return new MunicipalityId(UUID.randomUUID());
    }
}
