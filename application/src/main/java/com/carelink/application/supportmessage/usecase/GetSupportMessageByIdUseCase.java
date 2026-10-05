package com.carelink.application.supportmessage.usecase;

import com.carelink.application.supportmessage.dto.SupportMessageResponse;
import com.carelink.domain.supportmessage.port.repository.SupportMessageRepository;
import com.carelink.application.supportmessage.exception.SupportMessageNotFoundApplicationException;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;

public class GetSupportMessageByIdUseCase {
    private final SupportMessageRepository repository;

    public GetSupportMessageByIdUseCase(SupportMessageRepository repository) {
        this.repository = repository;
    }

    public SupportMessageResponse execute(SupportMessageId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SupportMessageNotFoundApplicationException(id.value().toString()));
        return new SupportMessageResponse(
                aggregate.id().value(),
                aggregate.conversationId().value(),
                aggregate.messageFormatId().value(),
                aggregate.memberId().value(),
                aggregate.payload(),
                aggregate.extraData(),
                aggregate.createdAt());
    }
}
