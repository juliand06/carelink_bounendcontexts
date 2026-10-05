package com.carelink.application.contactphonenumber.command;

import java.util.Objects;

import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record RegisterContactPhoneNumberCommand(
        TrustedContactId trustedContactId,
        String phoneNumber
) {
    public RegisterContactPhoneNumberCommand {
        Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        Objects.requireNonNull(phoneNumber, "phoneNumber must not be null");
    }
}
