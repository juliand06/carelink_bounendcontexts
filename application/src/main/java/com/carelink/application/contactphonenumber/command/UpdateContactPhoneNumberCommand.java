package com.carelink.application.contactphonenumber.command;

import java.util.Objects;

import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record UpdateContactPhoneNumberCommand(
        ContactPhoneNumberId id,
        TrustedContactId trustedContactId,
        String phoneNumber
) {
    public UpdateContactPhoneNumberCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        Objects.requireNonNull(phoneNumber, "phoneNumber must not be null");
    }
}
