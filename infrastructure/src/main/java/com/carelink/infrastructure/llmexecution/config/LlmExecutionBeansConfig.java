package com.carelink.infrastructure.llmexecution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.llmexecution.usecase.*;
import com.carelink.domain.llmexecution.port.repository.LlmExecutionRepository;
import com.carelink.infrastructure.llmexecution.adapters.out.persistence.mappers.LlmExecutionPersistenceMapper;
import com.carelink.infrastructure.llmexecution.adapters.out.persistence.repositories.LlmExecutionJpaRepository;
import com.carelink.infrastructure.llmexecution.adapters.out.persistence.repositories.LlmExecutionRepositoryAdapter;

@Configuration
public class LlmExecutionBeansConfig {
    @Bean
    public LlmExecutionPersistenceMapper llmExecutionPersistenceMapper() {
        return new LlmExecutionPersistenceMapper();
    }

    @Bean
    public LlmExecutionRepository llmExecutionRepository(LlmExecutionJpaRepository repository, LlmExecutionPersistenceMapper mapper) {
        return new LlmExecutionRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterLlmExecutionUseCase registerLlmExecutionUseCase(LlmExecutionRepository repository) {
        return new RegisterLlmExecutionUseCase(repository);
    }

    @Bean
    public GetLlmExecutionByIdUseCase getLlmExecutionByIdUseCase(LlmExecutionRepository repository) {
        return new GetLlmExecutionByIdUseCase(repository);
    }

    @Bean
    public ListLlmExecutionUseCase listLlmExecutionUseCase(LlmExecutionRepository repository) {
        return new ListLlmExecutionUseCase(repository);
    }

    @Bean
    public UpdateLlmExecutionUseCase updateLlmExecutionUseCase(LlmExecutionRepository repository) {
        return new UpdateLlmExecutionUseCase(repository);
    }

    @Bean
    public DeleteLlmExecutionUseCase deleteLlmExecutionUseCase(LlmExecutionRepository repository) {
        return new DeleteLlmExecutionUseCase(repository);
    }
}
