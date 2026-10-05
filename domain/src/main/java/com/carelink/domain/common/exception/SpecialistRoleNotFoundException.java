package com.carelink.domain.common.exception;

import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;

public class SpecialistRoleNotFoundException extends RuntimeException {
    public SpecialistRoleNotFoundException(SpecialistRoleId id) {
        super("SpecialistRole not found with id: " + id.value());
    }
}
