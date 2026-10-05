package com.carelink.application.supportmessage.usecase;

import java.util.List;

import com.carelink.application.supportmessage.dto.SupportMessageResponse;
import com.carelink.domain.supportmessage.port.repository.SupportMessageRepository;

public class ListSupportMessageUseCase {
    private final SupportMessageRepository repository;

    public ListSupportMessageUseCase(SupportMessageRepository repository) {
        this.repository = repository;
    }

    public List<SupportMessageResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new SupportMessageResponse(
                        aggregate.id().value(),
                        aggregate.conversationId().value(),
                        aggregate.messageFormatId().value(),
                        aggregate.memberId().value(),
                        aggregate.payload(),
                        aggregate.extraData(),
                        aggregate.createdAt()))
                .toList();
    }
}
