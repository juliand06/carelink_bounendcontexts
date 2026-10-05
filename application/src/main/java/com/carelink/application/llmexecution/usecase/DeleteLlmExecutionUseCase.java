package com.carelink.application.llmexecution.usecase;

import java.time.LocalDateTime;

import com.carelink.application.llmexecution.exception.LlmExecutionNotFoundApplicationException;
import com.carelink.domain.llmexecution.event.LlmExecutionDeletedEvent;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecution.port.repository.LlmExecutionRepository;

public class DeleteLlmExecutionUseCase {
    private final LlmExecutionRepository repository;

    public DeleteLlmExecutionUseCase(LlmExecutionRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionDeletedEvent execute(LlmExecutionId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmExecutionNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new LlmExecutionDeletedEvent(id, LocalDateTime.now());
    }
}
