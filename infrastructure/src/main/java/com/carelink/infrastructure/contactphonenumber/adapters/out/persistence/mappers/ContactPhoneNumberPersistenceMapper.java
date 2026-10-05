package com.carelink.infrastructure.contactphonenumber.adapters.out.persistence.mappers;

import com.carelink.domain.contactphonenumber.model.aggregate.ContactPhoneNumber;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.infrastructure.contactphonenumber.adapters.out.persistence.entity.ContactPhoneNumberJpaEntity;

public class ContactPhoneNumberPersistenceMapper {
    public ContactPhoneNumberJpaEntity toJpa(ContactPhoneNumber domain) {
        if (domain == null) {
            return null;
        }
        ContactPhoneNumberJpaEntity jpa = new ContactPhoneNumberJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setTrustedContactId(domain.trustedContactId().value());
        jpa.setPhoneNumber(domain.phoneNumber());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ContactPhoneNumber toDomain(ContactPhoneNumberJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return ContactPhoneNumber.restore(
                new ContactPhoneNumberId(jpa.getId()),
                new TrustedContactId(jpa.getTrustedContactId()),
                jpa.getPhoneNumber(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
