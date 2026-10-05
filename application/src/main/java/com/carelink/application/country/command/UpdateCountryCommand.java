package com.carelink.application.country.command;

import java.util.Objects;

import com.carelink.domain.country.model.valueobject.CountryId;

public record UpdateCountryCommand(
        CountryId id,
        String name,
        String isoCode,
        String notes,
        Boolean enabled,
        String dialPrefix
) {
    public UpdateCountryCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(isoCode, "isoCode must not be null");
        Objects.requireNonNull(enabled, "enabled must not be null");
    }
}
