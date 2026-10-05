package com.carelink.application.llmexecutionstatus.usecase;

import java.time.LocalDateTime;

import com.carelink.application.llmexecutionstatus.exception.LlmExecutionStatusNotFoundApplicationException;
import com.carelink.domain.llmexecutionstatus.event.LlmExecutionStatusDeletedEvent;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmexecutionstatus.port.repository.LlmExecutionStatusRepository;

public class DeleteLlmExecutionStatusUseCase {
    private final LlmExecutionStatusRepository repository;

    public DeleteLlmExecutionStatusUseCase(LlmExecutionStatusRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionStatusDeletedEvent execute(LlmExecutionStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmExecutionStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new LlmExecutionStatusDeletedEvent(id, LocalDateTime.now());
    }
}
