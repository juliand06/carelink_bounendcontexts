package com.carelink.application.country.command;

import java.util.Objects;

public record RegisterCountryCommand(
        String name,
        String isoCode,
        String notes,
        Boolean enabled,
        String dialPrefix
) {
    public RegisterCountryCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(isoCode, "isoCode must not be null");
        Objects.requireNonNull(enabled, "enabled must not be null");
    }
}
