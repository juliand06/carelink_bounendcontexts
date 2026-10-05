package com.carelink.application.messageformat.usecase;

import com.carelink.application.messageformat.dto.MessageFormatResponse;
import com.carelink.domain.messageformat.port.repository.MessageFormatRepository;
import com.carelink.application.messageformat.exception.MessageFormatNotFoundApplicationException;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;

public class GetMessageFormatByIdUseCase {
    private final MessageFormatRepository repository;

    public GetMessageFormatByIdUseCase(MessageFormatRepository repository) {
        this.repository = repository;
    }

    public MessageFormatResponse execute(MessageFormatId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MessageFormatNotFoundApplicationException(id.value().toString()));
        return new MessageFormatResponse(
                aggregate.id().value(),
                aggregate.label(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
