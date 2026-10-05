package com.carelink.application.contactrelationshiptype.usecase;

import com.carelink.application.contactrelationshiptype.command.UpdateContactRelationshipTypeCommand;
import com.carelink.application.contactrelationshiptype.dto.ContactRelationshipTypeResponse;
import com.carelink.domain.contactrelationshiptype.port.repository.ContactRelationshipTypeRepository;
import com.carelink.application.contactrelationshiptype.exception.ContactRelationshipTypeNotFoundApplicationException;

public class UpdateContactRelationshipTypeUseCase {
    private final ContactRelationshipTypeRepository repository;

    public UpdateContactRelationshipTypeUseCase(ContactRelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public ContactRelationshipTypeResponse execute(UpdateContactRelationshipTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ContactRelationshipTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.label());
        var saved = repository.save(aggregate);
        return new ContactRelationshipTypeResponse(
                saved.id().value(),
                saved.label());
    }
}
