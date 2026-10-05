package com.carelink.application.clienttrustedcontact.command;

import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record RegisterClientTrustedContactCommand(
        ClientId clientId,
        TrustedContactId trustedContactId
) {
    public RegisterClientTrustedContactCommand {
        Objects.requireNonNull(clientId, "clientId must not be null");
        Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
    }
}
