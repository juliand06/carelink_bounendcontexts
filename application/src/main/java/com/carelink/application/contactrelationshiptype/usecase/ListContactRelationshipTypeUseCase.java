package com.carelink.application.contactrelationshiptype.usecase;

import java.util.List;

import com.carelink.application.contactrelationshiptype.dto.ContactRelationshipTypeResponse;
import com.carelink.domain.contactrelationshiptype.port.repository.ContactRelationshipTypeRepository;

public class ListContactRelationshipTypeUseCase {
    private final ContactRelationshipTypeRepository repository;

    public ListContactRelationshipTypeUseCase(ContactRelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public List<ContactRelationshipTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ContactRelationshipTypeResponse(
                        aggregate.id().value(),
                        aggregate.label()))
                .toList();
    }
}
