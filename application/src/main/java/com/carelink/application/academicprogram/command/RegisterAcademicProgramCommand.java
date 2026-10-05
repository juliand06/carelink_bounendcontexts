package com.carelink.application.academicprogram.command;

import java.util.Objects;

public record RegisterAcademicProgramCommand(
        String title
) {
    public RegisterAcademicProgramCommand {
        Objects.requireNonNull(title, "title must not be null");
    }
}
