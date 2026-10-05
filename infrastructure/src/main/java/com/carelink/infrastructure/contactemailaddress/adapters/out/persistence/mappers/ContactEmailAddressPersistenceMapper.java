package com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.mappers;

import com.carelink.domain.contactemailaddress.model.aggregate.ContactEmailAddress;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.infrastructure.contactemailaddress.adapters.out.persistence.entity.ContactEmailAddressJpaEntity;

public class ContactEmailAddressPersistenceMapper {
    public ContactEmailAddressJpaEntity toJpa(ContactEmailAddress domain) {
        if (domain == null) {
            return null;
        }
        ContactEmailAddressJpaEntity jpa = new ContactEmailAddressJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setTrustedContactId(domain.trustedContactId().value());
        jpa.setEmailAddress(domain.emailAddress());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ContactEmailAddress toDomain(ContactEmailAddressJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return ContactEmailAddress.restore(
                new ContactEmailAddressId(jpa.getId()),
                new TrustedContactId(jpa.getTrustedContactId()),
                jpa.getEmailAddress(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
