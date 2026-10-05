package com.carelink.domain.common.exception;

import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;

public class ClientAllergyNotFoundException extends RuntimeException {
    public ClientAllergyNotFoundException(ClientAllergyId id) {
        super("ClientAllergy not found with id: " + id.value());
    }
}
