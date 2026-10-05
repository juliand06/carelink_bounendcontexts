package com.carelink.application.llmexecutionmetric.usecase;

import java.util.List;

import com.carelink.application.llmexecutionmetric.dto.LlmExecutionMetricResponse;
import com.carelink.domain.llmexecutionmetric.port.repository.LlmExecutionMetricRepository;

public class ListLlmExecutionMetricUseCase {
    private final LlmExecutionMetricRepository repository;

    public ListLlmExecutionMetricUseCase(LlmExecutionMetricRepository repository) {
        this.repository = repository;
    }

    public List<LlmExecutionMetricResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new LlmExecutionMetricResponse(
                        aggregate.id().value(),
                        aggregate.executionId().value(),
                        aggregate.inputTokens(),
                        aggregate.outputTokens(),
                        aggregate.totalTokens(),
                        aggregate.totalCost(),
                        aggregate.createdAt()))
                .toList();
    }
}
