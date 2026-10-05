package com.carelink.domain.department.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record DepartmentId(UUID value) {
    public DepartmentId {
        Objects.requireNonNull(value, "DepartmentId value must not be null");
    }

    public static DepartmentId generate() {
        return new DepartmentId(UUID.randomUUID());
    }
}
