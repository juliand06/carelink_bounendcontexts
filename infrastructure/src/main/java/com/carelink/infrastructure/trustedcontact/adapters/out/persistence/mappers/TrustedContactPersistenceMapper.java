package com.carelink.infrastructure.trustedcontact.adapters.out.persistence.mappers;

import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.trustedcontact.model.aggregate.TrustedContact;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.infrastructure.trustedcontact.adapters.out.persistence.entity.TrustedContactJpaEntity;

public class TrustedContactPersistenceMapper {
    public TrustedContactJpaEntity toJpa(TrustedContact domain) {
        if (domain == null) {
            return null;
        }
        TrustedContactJpaEntity jpa = new TrustedContactJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setFullName(domain.fullName());
        jpa.setRelationshipTypeId(domain.relationshipTypeId().value());
        jpa.setIsPrimaryContact(domain.isPrimaryContact());
        jpa.setNotes(domain.notes());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public TrustedContact toDomain(TrustedContactJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return TrustedContact.restore(
                new TrustedContactId(jpa.getId()),
                jpa.getFullName(),
                new ContactRelationshipTypeId(jpa.getRelationshipTypeId()),
                jpa.getIsPrimaryContact(),
                jpa.getNotes(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
