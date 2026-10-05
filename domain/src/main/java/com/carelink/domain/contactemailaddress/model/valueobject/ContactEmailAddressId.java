package com.carelink.domain.contactemailaddress.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ContactEmailAddressId(UUID value) {
    public ContactEmailAddressId {
        Objects.requireNonNull(value, "ContactEmailAddressId value must not be null");
    }

    public static ContactEmailAddressId generate() {
        return new ContactEmailAddressId(UUID.randomUUID());
    }
}
