package com.carelink.infrastructure.llmexecutionstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.llmexecutionstatus.usecase.*;
import com.carelink.domain.llmexecutionstatus.port.repository.LlmExecutionStatusRepository;
import com.carelink.infrastructure.llmexecutionstatus.adapters.out.persistence.mappers.LlmExecutionStatusPersistenceMapper;
import com.carelink.infrastructure.llmexecutionstatus.adapters.out.persistence.repositories.LlmExecutionStatusJpaRepository;
import com.carelink.infrastructure.llmexecutionstatus.adapters.out.persistence.repositories.LlmExecutionStatusRepositoryAdapter;

@Configuration
public class LlmExecutionStatusBeansConfig {
    @Bean
    public LlmExecutionStatusPersistenceMapper llmExecutionStatusPersistenceMapper() {
        return new LlmExecutionStatusPersistenceMapper();
    }

    @Bean
    public LlmExecutionStatusRepository llmExecutionStatusRepository(LlmExecutionStatusJpaRepository repository, LlmExecutionStatusPersistenceMapper mapper) {
        return new LlmExecutionStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterLlmExecutionStatusUseCase registerLlmExecutionStatusUseCase(LlmExecutionStatusRepository repository) {
        return new RegisterLlmExecutionStatusUseCase(repository);
    }

    @Bean
    public GetLlmExecutionStatusByIdUseCase getLlmExecutionStatusByIdUseCase(LlmExecutionStatusRepository repository) {
        return new GetLlmExecutionStatusByIdUseCase(repository);
    }

    @Bean
    public ListLlmExecutionStatusUseCase listLlmExecutionStatusUseCase(LlmExecutionStatusRepository repository) {
        return new ListLlmExecutionStatusUseCase(repository);
    }

    @Bean
    public UpdateLlmExecutionStatusUseCase updateLlmExecutionStatusUseCase(LlmExecutionStatusRepository repository) {
        return new UpdateLlmExecutionStatusUseCase(repository);
    }

    @Bean
    public DeleteLlmExecutionStatusUseCase deleteLlmExecutionStatusUseCase(LlmExecutionStatusRepository repository) {
        return new DeleteLlmExecutionStatusUseCase(repository);
    }
}
