package com.carelink.application.specialistrole.command;

import java.util.Objects;

import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;

public record UpdateSpecialistRoleCommand(
        SpecialistRoleId id,
        String title
) {
    public UpdateSpecialistRoleCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(title, "title must not be null");
    }
}
