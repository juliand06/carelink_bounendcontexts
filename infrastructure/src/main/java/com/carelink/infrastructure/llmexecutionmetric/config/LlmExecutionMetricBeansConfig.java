package com.carelink.infrastructure.llmexecutionmetric.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.llmexecutionmetric.usecase.*;
import com.carelink.domain.llmexecutionmetric.port.repository.LlmExecutionMetricRepository;
import com.carelink.infrastructure.llmexecutionmetric.adapters.out.persistence.mappers.LlmExecutionMetricPersistenceMapper;
import com.carelink.infrastructure.llmexecutionmetric.adapters.out.persistence.repositories.LlmExecutionMetricJpaRepository;
import com.carelink.infrastructure.llmexecutionmetric.adapters.out.persistence.repositories.LlmExecutionMetricRepositoryAdapter;

@Configuration
public class LlmExecutionMetricBeansConfig {
    @Bean
    public LlmExecutionMetricPersistenceMapper llmExecutionMetricPersistenceMapper() {
        return new LlmExecutionMetricPersistenceMapper();
    }

    @Bean
    public LlmExecutionMetricRepository llmExecutionMetricRepository(LlmExecutionMetricJpaRepository repository, LlmExecutionMetricPersistenceMapper mapper) {
        return new LlmExecutionMetricRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterLlmExecutionMetricUseCase registerLlmExecutionMetricUseCase(LlmExecutionMetricRepository repository) {
        return new RegisterLlmExecutionMetricUseCase(repository);
    }

    @Bean
    public GetLlmExecutionMetricByIdUseCase getLlmExecutionMetricByIdUseCase(LlmExecutionMetricRepository repository) {
        return new GetLlmExecutionMetricByIdUseCase(repository);
    }

    @Bean
    public ListLlmExecutionMetricUseCase listLlmExecutionMetricUseCase(LlmExecutionMetricRepository repository) {
        return new ListLlmExecutionMetricUseCase(repository);
    }

    @Bean
    public UpdateLlmExecutionMetricUseCase updateLlmExecutionMetricUseCase(LlmExecutionMetricRepository repository) {
        return new UpdateLlmExecutionMetricUseCase(repository);
    }

    @Bean
    public DeleteLlmExecutionMetricUseCase deleteLlmExecutionMetricUseCase(LlmExecutionMetricRepository repository) {
        return new DeleteLlmExecutionMetricUseCase(repository);
    }
}
