package com.carelink.application.messageformat.usecase;

import com.carelink.application.messageformat.command.UpdateMessageFormatCommand;
import com.carelink.application.messageformat.dto.MessageFormatResponse;
import com.carelink.domain.messageformat.port.repository.MessageFormatRepository;
import com.carelink.application.messageformat.exception.MessageFormatNotFoundApplicationException;

public class UpdateMessageFormatUseCase {
    private final MessageFormatRepository repository;

    public UpdateMessageFormatUseCase(MessageFormatRepository repository) {
        this.repository = repository;
    }

    public MessageFormatResponse execute(UpdateMessageFormatCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new MessageFormatNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.label());
        var saved = repository.save(aggregate);
        return new MessageFormatResponse(
                saved.id().value(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
