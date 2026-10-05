package com.carelink.domain.common.exception;

import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;

public class ContactRelationshipTypeNotFoundException extends RuntimeException {
    public ContactRelationshipTypeNotFoundException(ContactRelationshipTypeId id) {
        super("ContactRelationshipType not found with id: " + id.value());
    }
}
