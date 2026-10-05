package com.carelink.application.contactrelationshiptype.usecase;

import com.carelink.application.contactrelationshiptype.dto.ContactRelationshipTypeResponse;
import com.carelink.domain.contactrelationshiptype.port.repository.ContactRelationshipTypeRepository;
import com.carelink.application.contactrelationshiptype.exception.ContactRelationshipTypeNotFoundApplicationException;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;

public class GetContactRelationshipTypeByIdUseCase {
    private final ContactRelationshipTypeRepository repository;

    public GetContactRelationshipTypeByIdUseCase(ContactRelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public ContactRelationshipTypeResponse execute(ContactRelationshipTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ContactRelationshipTypeNotFoundApplicationException(id.value().toString()));
        return new ContactRelationshipTypeResponse(
                aggregate.id().value(),
                aggregate.label());
    }
}
