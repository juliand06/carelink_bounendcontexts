package com.carelink.application.supportmessage.usecase;

import com.carelink.application.supportmessage.command.UpdateSupportMessageCommand;
import com.carelink.application.supportmessage.dto.SupportMessageResponse;
import com.carelink.domain.supportmessage.port.repository.SupportMessageRepository;
import com.carelink.application.supportmessage.exception.SupportMessageNotFoundApplicationException;

public class UpdateSupportMessageUseCase {
    private final SupportMessageRepository repository;

    public UpdateSupportMessageUseCase(SupportMessageRepository repository) {
        this.repository = repository;
    }

    public SupportMessageResponse execute(UpdateSupportMessageCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new SupportMessageNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationId(),
                command.messageFormatId(),
                command.memberId(),
                command.payload(),
                command.extraData());
        var saved = repository.save(aggregate);
        return new SupportMessageResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.messageFormatId().value(),
                saved.memberId().value(),
                saved.payload(),
                saved.extraData(),
                saved.createdAt());
    }
}
