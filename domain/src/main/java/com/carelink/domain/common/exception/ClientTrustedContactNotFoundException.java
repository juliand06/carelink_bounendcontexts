package com.carelink.domain.common.exception;

import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;

public class ClientTrustedContactNotFoundException extends RuntimeException {
    public ClientTrustedContactNotFoundException(ClientTrustedContactId id) {
        super("ClientTrustedContact not found with id: " + id.value());
    }
}
