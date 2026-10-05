package com.carelink.infrastructure.llmmodel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.llmmodel.usecase.*;
import com.carelink.domain.llmmodel.port.repository.LlmModelRepository;
import com.carelink.infrastructure.llmmodel.adapters.out.persistence.mappers.LlmModelPersistenceMapper;
import com.carelink.infrastructure.llmmodel.adapters.out.persistence.repositories.LlmModelJpaRepository;
import com.carelink.infrastructure.llmmodel.adapters.out.persistence.repositories.LlmModelRepositoryAdapter;

@Configuration
public class LlmModelBeansConfig {
    @Bean
    public LlmModelPersistenceMapper llmModelPersistenceMapper() {
        return new LlmModelPersistenceMapper();
    }

    @Bean
    public LlmModelRepository llmModelRepository(LlmModelJpaRepository repository, LlmModelPersistenceMapper mapper) {
        return new LlmModelRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterLlmModelUseCase registerLlmModelUseCase(LlmModelRepository repository) {
        return new RegisterLlmModelUseCase(repository);
    }

    @Bean
    public GetLlmModelByIdUseCase getLlmModelByIdUseCase(LlmModelRepository repository) {
        return new GetLlmModelByIdUseCase(repository);
    }

    @Bean
    public ListLlmModelUseCase listLlmModelUseCase(LlmModelRepository repository) {
        return new ListLlmModelUseCase(repository);
    }

    @Bean
    public UpdateLlmModelUseCase updateLlmModelUseCase(LlmModelRepository repository) {
        return new UpdateLlmModelUseCase(repository);
    }

    @Bean
    public DeleteLlmModelUseCase deleteLlmModelUseCase(LlmModelRepository repository) {
        return new DeleteLlmModelUseCase(repository);
    }
}
