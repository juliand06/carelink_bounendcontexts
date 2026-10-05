package com.carelink.domain.contactrelationshiptype.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.contactrelationshiptype.model.aggregate.ContactRelationshipType;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;

public interface ContactRelationshipTypeRepository {
    ContactRelationshipType save(ContactRelationshipType aggregate);

    Optional<ContactRelationshipType> findById(ContactRelationshipTypeId id);

    List<ContactRelationshipType> findAll();

    void delete(ContactRelationshipType aggregate);
}
