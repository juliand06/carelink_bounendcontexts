package com.carelink.application.messageformat.usecase;

import java.util.List;

import com.carelink.application.messageformat.dto.MessageFormatResponse;
import com.carelink.domain.messageformat.port.repository.MessageFormatRepository;

public class ListMessageFormatUseCase {
    private final MessageFormatRepository repository;

    public ListMessageFormatUseCase(MessageFormatRepository repository) {
        this.repository = repository;
    }

    public List<MessageFormatResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new MessageFormatResponse(
                        aggregate.id().value(),
                        aggregate.label(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
