package com.carelink.application.clienttrustedcontact.command;

import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record UpdateClientTrustedContactCommand(
        ClientTrustedContactId id,
        ClientId clientId,
        TrustedContactId trustedContactId
) {
    public UpdateClientTrustedContactCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(clientId, "clientId must not be null");
        Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
    }
}
