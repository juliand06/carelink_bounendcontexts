package com.carelink.application.specialistrole.command;

import java.util.Objects;

public record RegisterSpecialistRoleCommand(
        String title
) {
    public RegisterSpecialistRoleCommand {
        Objects.requireNonNull(title, "title must not be null");
    }
}
