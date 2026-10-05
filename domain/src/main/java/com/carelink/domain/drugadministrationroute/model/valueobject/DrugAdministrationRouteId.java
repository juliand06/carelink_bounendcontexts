package com.carelink.domain.drugadministrationroute.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record DrugAdministrationRouteId(UUID value) {
    public DrugAdministrationRouteId {
        Objects.requireNonNull(value, "DrugAdministrationRouteId value must not be null");
    }

    public static DrugAdministrationRouteId generate() {
        return new DrugAdministrationRouteId(UUID.randomUUID());
    }
}
