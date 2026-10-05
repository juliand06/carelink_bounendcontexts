package com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.mappers;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clienttrustedcontact.model.aggregate.ClientTrustedContact;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.entity.ClientTrustedContactJpaEntity;

public class ClientTrustedContactPersistenceMapper {
    public ClientTrustedContactJpaEntity toJpa(ClientTrustedContact domain) {
        if (domain == null) {
            return null;
        }
        ClientTrustedContactJpaEntity jpa = new ClientTrustedContactJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setClientId(domain.clientId().value());
        jpa.setTrustedContactId(domain.trustedContactId().value());
        jpa.setCreatedAt(domain.createdAt());
        return jpa;
    }

    public ClientTrustedContact toDomain(ClientTrustedContactJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return ClientTrustedContact.restore(
                new ClientTrustedContactId(jpa.getId()),
                new ClientId(jpa.getClientId()),
                new TrustedContactId(jpa.getTrustedContactId()),
                jpa.getCreatedAt());
    }
}
