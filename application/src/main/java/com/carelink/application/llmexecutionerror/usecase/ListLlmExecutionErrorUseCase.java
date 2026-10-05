package com.carelink.application.llmexecutionerror.usecase;

import java.util.List;

import com.carelink.application.llmexecutionerror.dto.LlmExecutionErrorResponse;
import com.carelink.domain.llmexecutionerror.port.repository.LlmExecutionErrorRepository;

public class ListLlmExecutionErrorUseCase {
    private final LlmExecutionErrorRepository repository;

    public ListLlmExecutionErrorUseCase(LlmExecutionErrorRepository repository) {
        this.repository = repository;
    }

    public List<LlmExecutionErrorResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new LlmExecutionErrorResponse(
                        aggregate.id().value(),
                        aggregate.executionId().value(),
                        aggregate.errorDetail(),
                        aggregate.errorCode(),
                        aggregate.vendorErrorRef(),
                        aggregate.createdAt()))
                .toList();
    }
}
