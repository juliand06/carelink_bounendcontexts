package com.carelink.domain.common.exception;

import com.carelink.domain.client.model.valueobject.ClientId;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(ClientId id) {
        super("Client not found with id: " + id.value());
    }
}
