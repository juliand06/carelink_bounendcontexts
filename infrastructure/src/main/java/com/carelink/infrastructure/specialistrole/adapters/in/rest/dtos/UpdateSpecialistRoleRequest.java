package com.carelink.infrastructure.specialistrole.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateSpecialistRoleRequest(
        @NotNull(message = "title is required")
        @Size(max = 40, message = "title must have at most 40 characters")
        String title
) {
}
