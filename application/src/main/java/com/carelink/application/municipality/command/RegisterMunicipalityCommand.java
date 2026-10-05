package com.carelink.application.municipality.command;

import java.util.Objects;

import com.carelink.domain.department.model.valueobject.DepartmentId;

public record RegisterMunicipalityCommand(
        String name,
        String isoCode,
        String notes,
        Boolean enabled,
        DepartmentId departmentId
) {
    public RegisterMunicipalityCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(isoCode, "isoCode must not be null");
        Objects.requireNonNull(enabled, "enabled must not be null");
        Objects.requireNonNull(departmentId, "departmentId must not be null");
    }
}
