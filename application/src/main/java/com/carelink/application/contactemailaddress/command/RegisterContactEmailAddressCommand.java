package com.carelink.application.contactemailaddress.command;

import java.util.Objects;

import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record RegisterContactEmailAddressCommand(
        TrustedContactId trustedContactId,
        String emailAddress
) {
    public RegisterContactEmailAddressCommand {
        Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        Objects.requireNonNull(emailAddress, "emailAddress must not be null");
    }
}
