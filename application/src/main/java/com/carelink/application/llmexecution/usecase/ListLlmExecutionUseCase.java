package com.carelink.application.llmexecution.usecase;

import java.util.List;

import com.carelink.application.llmexecution.dto.LlmExecutionResponse;
import com.carelink.domain.llmexecution.port.repository.LlmExecutionRepository;

public class ListLlmExecutionUseCase {
    private final LlmExecutionRepository repository;

    public ListLlmExecutionUseCase(LlmExecutionRepository repository) {
        this.repository = repository;
    }

    public List<LlmExecutionResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new LlmExecutionResponse(
                        aggregate.id().value(),
                        aggregate.conversationId().value(),
                        aggregate.messageId().value(),
                        aggregate.modelId().value(),
                        aggregate.executionStatusId().value(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
