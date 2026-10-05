package com.carelink.domain.common.exception;

import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;

public class SpecialistCredentialNotFoundException extends RuntimeException {
    public SpecialistCredentialNotFoundException(SpecialistCredentialId id) {
        super("SpecialistCredential not found with id: " + id.value());
    }
}
