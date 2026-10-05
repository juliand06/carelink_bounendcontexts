package com.carelink.application.messageformat.usecase;

import com.carelink.application.messageformat.command.RegisterMessageFormatCommand;
import com.carelink.application.messageformat.dto.MessageFormatResponse;
import com.carelink.domain.messageformat.port.repository.MessageFormatRepository;
import com.carelink.domain.messageformat.model.aggregate.MessageFormat;

public class RegisterMessageFormatUseCase {
    private final MessageFormatRepository repository;

    public RegisterMessageFormatUseCase(MessageFormatRepository repository) {
        this.repository = repository;
    }

    public MessageFormatResponse execute(RegisterMessageFormatCommand command) {
        MessageFormat aggregate = MessageFormat.register(
                command.label());
        MessageFormat saved = repository.save(aggregate);
        return new MessageFormatResponse(
                saved.id().value(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
