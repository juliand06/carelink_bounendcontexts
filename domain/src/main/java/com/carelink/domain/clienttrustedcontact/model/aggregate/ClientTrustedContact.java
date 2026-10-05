package com.carelink.domain.clienttrustedcontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clienttrustedcontact.event.ClientTrustedContactRegisteredEvent;
import com.carelink.domain.clienttrustedcontact.event.ClientTrustedContactUpdatedEvent;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public class ClientTrustedContact extends AggregateRoot {
    private final ClientTrustedContactId id;
    private ClientId clientId;
    private TrustedContactId trustedContactId;
    private final LocalDateTime createdAt;

    private ClientTrustedContact(
            ClientTrustedContactId id,
            ClientId clientId,
            TrustedContactId trustedContactId,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.clientId = Objects.requireNonNull(clientId, "clientId must not be null");
        this.trustedContactId = Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static ClientTrustedContact register(
            ClientId clientId,
            TrustedContactId trustedContactId) {
        ClientTrustedContactId id = ClientTrustedContactId.generate();
        LocalDateTime now = LocalDateTime.now();
        ClientTrustedContact aggregate = new ClientTrustedContact(
                id,
                clientId,
                trustedContactId,
                now);
        aggregate.recordEvent(new ClientTrustedContactRegisteredEvent(id, now));
        return aggregate;
    }

    public static ClientTrustedContact restore(
            ClientTrustedContactId id,
            ClientId clientId,
            TrustedContactId trustedContactId,
            LocalDateTime createdAt) {
        return new ClientTrustedContact(
                id,
                clientId,
                trustedContactId,
                createdAt);
    }

    public void update(
            ClientId clientId,
            TrustedContactId trustedContactId) {
        this.clientId = Objects.requireNonNull(clientId, "clientId must not be null");
        this.trustedContactId = Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new ClientTrustedContactUpdatedEvent(
                this.id,
                this.clientId,
                this.trustedContactId,
                now));
    }

    public ClientTrustedContactId id() {
        return id;
    }

    public ClientId clientId() {
        return clientId;
    }

    public TrustedContactId trustedContactId() {
        return trustedContactId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
