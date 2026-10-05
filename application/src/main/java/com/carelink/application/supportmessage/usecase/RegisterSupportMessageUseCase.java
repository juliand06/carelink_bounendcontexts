package com.carelink.application.supportmessage.usecase;

import com.carelink.application.supportmessage.command.RegisterSupportMessageCommand;
import com.carelink.application.supportmessage.dto.SupportMessageResponse;
import com.carelink.domain.supportmessage.port.repository.SupportMessageRepository;
import com.carelink.domain.supportmessage.model.aggregate.SupportMessage;

public class RegisterSupportMessageUseCase {
    private final SupportMessageRepository repository;

    public RegisterSupportMessageUseCase(SupportMessageRepository repository) {
        this.repository = repository;
    }

    public SupportMessageResponse execute(RegisterSupportMessageCommand command) {
        SupportMessage aggregate = SupportMessage.register(
                command.conversationId(),
                command.messageFormatId(),
                command.memberId(),
                command.payload(),
                command.extraData());
        SupportMessage saved = repository.save(aggregate);
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
