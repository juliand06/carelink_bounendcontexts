package com.carelink.application.llmexecutionstatus.usecase;

import com.carelink.application.llmexecutionstatus.dto.LlmExecutionStatusResponse;
import com.carelink.domain.llmexecutionstatus.port.repository.LlmExecutionStatusRepository;
import com.carelink.application.llmexecutionstatus.exception.LlmExecutionStatusNotFoundApplicationException;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;

public class GetLlmExecutionStatusByIdUseCase {
    private final LlmExecutionStatusRepository repository;

    public GetLlmExecutionStatusByIdUseCase(LlmExecutionStatusRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionStatusResponse execute(LlmExecutionStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmExecutionStatusNotFoundApplicationException(id.value().toString()));
        return new LlmExecutionStatusResponse(
                aggregate.id().value(),
                aggregate.label(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
