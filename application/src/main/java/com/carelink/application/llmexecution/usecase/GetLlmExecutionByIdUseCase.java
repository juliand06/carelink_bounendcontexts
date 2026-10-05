package com.carelink.application.llmexecution.usecase;

import com.carelink.application.llmexecution.dto.LlmExecutionResponse;
import com.carelink.domain.llmexecution.port.repository.LlmExecutionRepository;
import com.carelink.application.llmexecution.exception.LlmExecutionNotFoundApplicationException;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;

public class GetLlmExecutionByIdUseCase {
    private final LlmExecutionRepository repository;

    public GetLlmExecutionByIdUseCase(LlmExecutionRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionResponse execute(LlmExecutionId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmExecutionNotFoundApplicationException(id.value().toString()));
        return new LlmExecutionResponse(
                aggregate.id().value(),
                aggregate.conversationId().value(),
                aggregate.messageId().value(),
                aggregate.modelId().value(),
                aggregate.executionStatusId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
