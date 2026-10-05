package com.carelink.application.supportconversationstatus.usecase;

import com.carelink.application.supportconversationstatus.command.UpdateSupportConversationStatusCommand;
import com.carelink.application.supportconversationstatus.dto.SupportConversationStatusResponse;
import com.carelink.domain.supportconversationstatus.port.repository.SupportConversationStatusRepository;
import com.carelink.application.supportconversationstatus.exception.SupportConversationStatusNotFoundApplicationException;

public class UpdateSupportConversationStatusUseCase {
    private final SupportConversationStatusRepository repository;

    public UpdateSupportConversationStatusUseCase(SupportConversationStatusRepository repository) {
        this.repository = repository;
    }

    public SupportConversationStatusResponse execute(UpdateSupportConversationStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new SupportConversationStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label());
        var saved = repository.save(aggregate);
        return new SupportConversationStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
