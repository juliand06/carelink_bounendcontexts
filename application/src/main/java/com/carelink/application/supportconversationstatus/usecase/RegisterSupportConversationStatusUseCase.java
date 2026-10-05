package com.carelink.application.supportconversationstatus.usecase;

import com.carelink.application.supportconversationstatus.command.RegisterSupportConversationStatusCommand;
import com.carelink.application.supportconversationstatus.dto.SupportConversationStatusResponse;
import com.carelink.domain.supportconversationstatus.port.repository.SupportConversationStatusRepository;
import com.carelink.domain.supportconversationstatus.model.aggregate.SupportConversationStatus;

public class RegisterSupportConversationStatusUseCase {
    private final SupportConversationStatusRepository repository;

    public RegisterSupportConversationStatusUseCase(SupportConversationStatusRepository repository) {
        this.repository = repository;
    }

    public SupportConversationStatusResponse execute(RegisterSupportConversationStatusCommand command) {
        SupportConversationStatus aggregate = SupportConversationStatus.register(
                command.code(),
                command.label());
        SupportConversationStatus saved = repository.save(aggregate);
        return new SupportConversationStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
