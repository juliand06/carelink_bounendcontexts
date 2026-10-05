package com.carelink.domain.contactphonenumber.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ContactPhoneNumberId(UUID value) {
    public ContactPhoneNumberId {
        Objects.requireNonNull(value, "ContactPhoneNumberId value must not be null");
    }

    public static ContactPhoneNumberId generate() {
        return new ContactPhoneNumberId(UUID.randomUUID());
    }
}
