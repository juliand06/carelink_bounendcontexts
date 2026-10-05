package com.carelink.application.llmmodel.usecase;

import java.time.LocalDateTime;

import com.carelink.application.llmmodel.exception.LlmModelNotFoundApplicationException;
import com.carelink.domain.llmmodel.event.LlmModelDeletedEvent;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.llmmodel.port.repository.LlmModelRepository;

public class DeleteLlmModelUseCase {
    private final LlmModelRepository repository;

    public DeleteLlmModelUseCase(LlmModelRepository repository) {
        this.repository = repository;
    }

    public LlmModelDeletedEvent execute(LlmModelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmModelNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new LlmModelDeletedEvent(id, LocalDateTime.now());
    }
}
