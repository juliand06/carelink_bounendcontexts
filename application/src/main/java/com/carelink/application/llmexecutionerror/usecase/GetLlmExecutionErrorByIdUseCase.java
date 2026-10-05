package com.carelink.application.llmexecutionerror.usecase;

import com.carelink.application.llmexecutionerror.dto.LlmExecutionErrorResponse;
import com.carelink.domain.llmexecutionerror.port.repository.LlmExecutionErrorRepository;
import com.carelink.application.llmexecutionerror.exception.LlmExecutionErrorNotFoundApplicationException;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;

public class GetLlmExecutionErrorByIdUseCase {
    private final LlmExecutionErrorRepository repository;

    public GetLlmExecutionErrorByIdUseCase(LlmExecutionErrorRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionErrorResponse execute(LlmExecutionErrorId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new LlmExecutionErrorNotFoundApplicationException(id.value().toString()));
        return new LlmExecutionErrorResponse(
                aggregate.id().value(),
                aggregate.executionId().value(),
                aggregate.errorDetail(),
                aggregate.errorCode(),
                aggregate.vendorErrorRef(),
                aggregate.createdAt());
    }
}
