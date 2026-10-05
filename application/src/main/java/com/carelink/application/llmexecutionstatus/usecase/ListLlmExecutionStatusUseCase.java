package com.carelink.application.llmexecutionstatus.usecase;

import java.util.List;

import com.carelink.application.llmexecutionstatus.dto.LlmExecutionStatusResponse;
import com.carelink.domain.llmexecutionstatus.port.repository.LlmExecutionStatusRepository;

public class ListLlmExecutionStatusUseCase {
    private final LlmExecutionStatusRepository repository;

    public ListLlmExecutionStatusUseCase(LlmExecutionStatusRepository repository) {
        this.repository = repository;
    }

    public List<LlmExecutionStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new LlmExecutionStatusResponse(
                        aggregate.id().value(),
                        aggregate.label(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
