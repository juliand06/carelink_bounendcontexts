package com.carelink.application.department.command;

import java.util.Objects;

import com.carelink.domain.country.model.valueobject.CountryId;

public record RegisterDepartmentCommand(
        String name,
        String isoCode,
        String notes,
        Boolean enabled,
        CountryId countryId
) {
    public RegisterDepartmentCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(isoCode, "isoCode must not be null");
        Objects.requireNonNull(enabled, "enabled must not be null");
        Objects.requireNonNull(countryId, "countryId must not be null");
    }
}
