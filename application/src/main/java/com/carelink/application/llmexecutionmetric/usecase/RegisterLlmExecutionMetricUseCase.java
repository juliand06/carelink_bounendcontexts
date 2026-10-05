package com.carelink.application.llmexecutionmetric.usecase;

import com.carelink.application.llmexecutionmetric.command.RegisterLlmExecutionMetricCommand;
import com.carelink.application.llmexecutionmetric.dto.LlmExecutionMetricResponse;
import com.carelink.domain.llmexecutionmetric.port.repository.LlmExecutionMetricRepository;
import com.carelink.domain.llmexecutionmetric.model.aggregate.LlmExecutionMetric;

public class RegisterLlmExecutionMetricUseCase {
    private final LlmExecutionMetricRepository repository;

    public RegisterLlmExecutionMetricUseCase(LlmExecutionMetricRepository repository) {
        this.repository = repository;
    }

    public LlmExecutionMetricResponse execute(RegisterLlmExecutionMetricCommand command) {
        LlmExecutionMetric aggregate = LlmExecutionMetric.register(
                command.executionId(),
                command.inputTokens(),
                command.outputTokens(),
                command.totalTokens(),
                command.totalCost());
        LlmExecutionMetric saved = repository.save(aggregate);
        return new LlmExecutionMetricResponse(
                saved.id().value(),
                saved.executionId().value(),
                saved.inputTokens(),
                saved.outputTokens(),
                saved.totalTokens(),
                saved.totalCost(),
                saved.createdAt());
    }
}
