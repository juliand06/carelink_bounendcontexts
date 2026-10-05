package com.carelink.application.drugadministrationroute.command;

import java.util.Objects;

public record RegisterDrugAdministrationRouteCommand(
        String code,
        String label,
        Boolean active
) {
    public RegisterDrugAdministrationRouteCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
