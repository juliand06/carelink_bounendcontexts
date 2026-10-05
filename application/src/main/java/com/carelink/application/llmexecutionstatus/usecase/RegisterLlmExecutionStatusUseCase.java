package com.carelink.application.llmexecutionstatus.usecase;

import com.carelink.application.llmexecutionstatus.command.RegisterLlmExecutionStatusCommand;
import com.carelink.application.llmexecutionstatus.dto.LlmExecutionStatusResponse;
import com.carelink.domain.llmexecutionstatus.port.repository.LlmExecutionStatusRepository;
import com.carelink.domain.llmexecutionstatus.model.aggregate.LlmExecutionStatus;

public class RegisterLlmExecutionStatusUseCase {
    private final LlmExecutionStatusRepository repository;

    public RegisterLlmExecutionStatusUseCase(LlmExecutionStatusRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionStatusResponse execute(RegisterLlmExecutionStatusCommand command) {
        LlmExecutionStatus aggregate = LlmExecutionStatus.register(
                command.label());
        LlmExecutionStatus saved = repository.save(aggregate);
        return new LlmExecutionStatusResponse(
                saved.id().value(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
