package com.carelink.application.drugadministrationroute.command;

import java.util.Objects;

import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;

public record UpdateDrugAdministrationRouteCommand(
        DrugAdministrationRouteId id,
        String code,
        String label,
        Boolean active
) {
    public UpdateDrugAdministrationRouteCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
