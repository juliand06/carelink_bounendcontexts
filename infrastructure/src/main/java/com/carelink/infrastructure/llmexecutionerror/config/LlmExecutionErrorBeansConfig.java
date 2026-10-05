package com.carelink.infrastructure.llmexecutionerror.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.llmexecutionerror.usecase.*;
import com.carelink.domain.llmexecutionerror.port.repository.LlmExecutionErrorRepository;
import com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.mappers.LlmExecutionErrorPersistenceMapper;
import com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.repositories.LlmExecutionErrorJpaRepository;
import com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.repositories.LlmExecutionErrorRepositoryAdapter;

@Configuration
public class LlmExecutionErrorBeansConfig {
    @Bean
    public LlmExecutionErrorPersistenceMapper llmExecutionErrorPersistenceMapper() {
        return new LlmExecutionErrorPersistenceMapper();
    }

    @Bean
    public LlmExecutionErrorRepository llmExecutionErrorRepository(LlmExecutionErrorJpaRepository repository, LlmExecutionErrorPersistenceMapper mapper) {
        return new LlmExecutionErrorRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterLlmExecutionErrorUseCase registerLlmExecutionErrorUseCase(LlmExecutionErrorRepository repository) {
        return new RegisterLlmExecutionErrorUseCase(repository);
    }

    @Bean
    public GetLlmExecutionErrorByIdUseCase getLlmExecutionErrorByIdUseCase(LlmExecutionErrorRepository repository) {
        return new GetLlmExecutionErrorByIdUseCase(repository);
    }

    @Bean
    public ListLlmExecutionErrorUseCase listLlmExecutionErrorUseCase(LlmExecutionErrorRepository repository) {
        return new ListLlmExecutionErrorUseCase(repository);
    }

    @Bean
    public UpdateLlmExecutionErrorUseCase updateLlmExecutionErrorUseCase(LlmExecutionErrorRepository repository) {
        return new UpdateLlmExecutionErrorUseCase(repository);
    }

    @Bean
    public DeleteLlmExecutionErrorUseCase deleteLlmExecutionErrorUseCase(LlmExecutionErrorRepository repository) {
        return new DeleteLlmExecutionErrorUseCase(repository);
    }
}
