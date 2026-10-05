package com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.mappers;

import com.carelink.domain.contactrelationshiptype.model.aggregate.ContactRelationshipType;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.entity.ContactRelationshipTypeJpaEntity;

public class ContactRelationshipTypePersistenceMapper {
    public ContactRelationshipTypeJpaEntity toJpa(ContactRelationshipType domain) {
        if (domain == null) {
            return null;
        }
        ContactRelationshipTypeJpaEntity jpa = new ContactRelationshipTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setLabel(domain.label());
        return jpa;
    }

    public ContactRelationshipType toDomain(ContactRelationshipTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return ContactRelationshipType.restore(
                new ContactRelationshipTypeId(jpa.getId()),
                jpa.getLabel());
    }
}
