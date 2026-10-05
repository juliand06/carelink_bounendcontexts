package com.carelink.application.llmexecutionerror.usecase;

import java.time.LocalDateTime;

import com.carelink.application.llmexecutionerror.exception.LlmExecutionErrorNotFoundApplicationException;
import com.carelink.domain.llmexecutionerror.event.LlmExecutionErrorDeletedEvent;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;
import com.carelink.domain.llmexecutionerror.port.repository.LlmExecutionErrorRepository;

public class DeleteLlmExecutionErrorUseCase {
    private final LlmExecutionErrorRepository repository;

    public DeleteLlmExecutionErrorUseCase(LlmExecutionErrorRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionErrorDeletedEvent execute(LlmExecutionErrorId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmExecutionErrorNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new LlmExecutionErrorDeletedEvent(id, LocalDateTime.now());
    }
}
