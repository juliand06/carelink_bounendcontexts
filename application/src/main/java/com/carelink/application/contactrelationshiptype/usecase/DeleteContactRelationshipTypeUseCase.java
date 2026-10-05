package com.carelink.application.contactrelationshiptype.usecase;

import java.time.LocalDateTime;

import com.carelink.application.contactrelationshiptype.exception.ContactRelationshipTypeNotFoundApplicationException;
import com.carelink.domain.contactrelationshiptype.event.ContactRelationshipTypeDeletedEvent;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.contactrelationshiptype.port.repository.ContactRelationshipTypeRepository;

public class DeleteContactRelationshipTypeUseCase {
    private final ContactRelationshipTypeRepository repository;

    public DeleteContactRelationshipTypeUseCase(ContactRelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public ContactRelationshipTypeDeletedEvent execute(ContactRelationshipTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ContactRelationshipTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ContactRelationshipTypeDeletedEvent(id, LocalDateTime.now());
    }
}
