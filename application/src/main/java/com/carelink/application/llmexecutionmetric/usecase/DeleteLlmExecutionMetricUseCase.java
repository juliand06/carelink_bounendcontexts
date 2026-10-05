package com.carelink.application.llmexecutionmetric.usecase;

import java.time.LocalDateTime;

import com.carelink.application.llmexecutionmetric.exception.LlmExecutionMetricNotFoundApplicationException;
import com.carelink.domain.llmexecutionmetric.event.LlmExecutionMetricDeletedEvent;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;
import com.carelink.domain.llmexecutionmetric.port.repository.LlmExecutionMetricRepository;

public class DeleteLlmExecutionMetricUseCase {
    private final LlmExecutionMetricRepository repository;

    public DeleteLlmExecutionMetricUseCase(LlmExecutionMetricRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionMetricDeletedEvent execute(LlmExecutionMetricId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmExecutionMetricNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new LlmExecutionMetricDeletedEvent(id, LocalDateTime.now());
    }
}
