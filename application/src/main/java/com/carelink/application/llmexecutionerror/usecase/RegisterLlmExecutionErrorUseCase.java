package com.carelink.application.llmexecutionerror.usecase;

import com.carelink.application.llmexecutionerror.command.RegisterLlmExecutionErrorCommand;
import com.carelink.application.llmexecutionerror.dto.LlmExecutionErrorResponse;
import com.carelink.domain.llmexecutionerror.port.repository.LlmExecutionErrorRepository;
import com.carelink.domain.llmexecutionerror.model.aggregate.LlmExecutionError;

public class RegisterLlmExecutionErrorUseCase {
    private final LlmExecutionErrorRepository repository;

    public RegisterLlmExecutionErrorUseCase(LlmExecutionErrorRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionErrorResponse execute(RegisterLlmExecutionErrorCommand command) {
        LlmExecutionError aggregate = LlmExecutionError.register(
                command.executionId(),
                command.errorDetail(),
                command.errorCode(),
                command.vendorErrorRef());
        LlmExecutionError saved = repository.save(aggregate);
        return new LlmExecutionErrorResponse(
                saved.id().value(),
                saved.executionId().value(),
                saved.errorDetail(),
                saved.errorCode(),
                saved.vendorErrorRef(),
                saved.createdAt());
    }
}
