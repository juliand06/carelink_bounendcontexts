package com.carelink.domain.common.exception;

import com.carelink.domain.municipality.model.valueobject.MunicipalityId;

public class MunicipalityNotFoundException extends RuntimeException {
    public MunicipalityNotFoundException(MunicipalityId id) {
        super("Municipality not found with id: " + id.value());
    }
}
