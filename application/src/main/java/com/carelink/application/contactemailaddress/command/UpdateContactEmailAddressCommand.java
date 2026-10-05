package com.carelink.application.contactemailaddress.command;

import java.util.Objects;

import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record UpdateContactEmailAddressCommand(
        ContactEmailAddressId id,
        TrustedContactId trustedContactId,
        String emailAddress
) {
    public UpdateContactEmailAddressCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        Objects.requireNonNull(emailAddress, "emailAddress must not be null");
    }
}
