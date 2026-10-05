package com.carelink.application.llmexecution.usecase;

import com.carelink.application.llmexecution.command.RegisterLlmExecutionCommand;
import com.carelink.application.llmexecution.dto.LlmExecutionResponse;
import com.carelink.domain.llmexecution.port.repository.LlmExecutionRepository;
import com.carelink.domain.llmexecution.model.aggregate.LlmExecution;

public class RegisterLlmExecutionUseCase {
    private final LlmExecutionRepository repository;

    public RegisterLlmExecutionUseCase(LlmExecutionRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionResponse execute(RegisterLlmExecutionCommand command) {
        LlmExecution aggregate = LlmExecution.register(
                command.conversationId(),
                command.messageId(),
                command.modelId(),
                command.executionStatusId());
        LlmExecution saved = repository.save(aggregate);
        return new LlmExecutionResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.messageId().value(),
                saved.modelId().value(),
                saved.executionStatusId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
