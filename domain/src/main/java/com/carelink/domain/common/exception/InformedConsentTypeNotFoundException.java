package com.carelink.domain.common.exception;

import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;

public class InformedConsentTypeNotFoundException extends RuntimeException {
    public InformedConsentTypeNotFoundException(InformedConsentTypeId id) {
        super("InformedConsentType not found with id: " + id.value());
    }
}
