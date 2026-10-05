package com.carelink.domain.common.exception;

import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class SpecialistNotFoundException extends RuntimeException {
    public SpecialistNotFoundException(SpecialistId id) {
        super("Specialist not found with id: " + id.value());
    }
}
