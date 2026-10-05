package com.carelink.application.biologicalsex.command;

import java.util.Objects;

import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;

public record UpdateBiologicalSexCommand(
        BiologicalSexId id,
        String label
) {
    public UpdateBiologicalSexCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(label, "label must not be null");
    }
}
