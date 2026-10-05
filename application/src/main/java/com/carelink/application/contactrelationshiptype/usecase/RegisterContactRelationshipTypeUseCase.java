package com.carelink.application.contactrelationshiptype.usecase;

import com.carelink.application.contactrelationshiptype.command.RegisterContactRelationshipTypeCommand;
import com.carelink.application.contactrelationshiptype.dto.ContactRelationshipTypeResponse;
import com.carelink.domain.contactrelationshiptype.port.repository.ContactRelationshipTypeRepository;
import com.carelink.domain.contactrelationshiptype.model.aggregate.ContactRelationshipType;

public class RegisterContactRelationshipTypeUseCase {
    private final ContactRelationshipTypeRepository repository;

    public RegisterContactRelationshipTypeUseCase(ContactRelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public ContactRelationshipTypeResponse execute(RegisterContactRelationshipTypeCommand command) {
        ContactRelationshipType aggregate = ContactRelationshipType.register(
                command.label());
        ContactRelationshipType saved = repository.save(aggregate);
        return new ContactRelationshipTypeResponse(
                saved.id().value(),
                saved.label());
    }
}
