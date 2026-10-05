package com.carelink.domain.common.exception;

import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public class TrustedContactNotFoundException extends RuntimeException {
    public TrustedContactNotFoundException(TrustedContactId id) {
        super("TrustedContact not found with id: " + id.value());
    }
}
