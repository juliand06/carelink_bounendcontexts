package com.carelink.application.llmexecutionerror.usecase;

import com.carelink.application.llmexecutionerror.command.UpdateLlmExecutionErrorCommand;
import com.carelink.application.llmexecutionerror.dto.LlmExecutionErrorResponse;
import com.carelink.domain.llmexecutionerror.port.repository.LlmExecutionErrorRepository;
import com.carelink.application.llmexecutionerror.exception.LlmExecutionErrorNotFoundApplicationException;

public class UpdateLlmExecutionErrorUseCase {
    private final LlmExecutionErrorRepository repository;

    public UpdateLlmExecutionErrorUseCase(LlmExecutionErrorRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionErrorResponse execute(UpdateLlmExecutionErrorCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new LlmExecutionErrorNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.executionId(),
                command.errorDetail(),
                command.errorCode(),
                command.vendorErrorRef());
        var saved = repository.save(aggregate);
        return new LlmExecutionErrorResponse(
                saved.id().value(),
                saved.executionId().value(),
                saved.errorDetail(),
                saved.errorCode(),
                saved.vendorErrorRef(),
                saved.createdAt());
    }
}
