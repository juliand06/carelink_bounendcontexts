package com.carelink.infrastructure.llmvendor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.llmvendor.usecase.*;
import com.carelink.domain.llmvendor.port.repository.LlmVendorRepository;
import com.carelink.infrastructure.llmvendor.adapters.out.persistence.mappers.LlmVendorPersistenceMapper;
import com.carelink.infrastructure.llmvendor.adapters.out.persistence.repositories.LlmVendorJpaRepository;
import com.carelink.infrastructure.llmvendor.adapters.out.persistence.repositories.LlmVendorRepositoryAdapter;

@Configuration
public class LlmVendorBeansConfig {
    @Bean
    public LlmVendorPersistenceMapper llmVendorPersistenceMapper() {
        return new LlmVendorPersistenceMapper();
    }

    @Bean
    public LlmVendorRepository llmVendorRepository(LlmVendorJpaRepository repository, LlmVendorPersistenceMapper mapper) {
        return new LlmVendorRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterLlmVendorUseCase registerLlmVendorUseCase(LlmVendorRepository repository) {
        return new RegisterLlmVendorUseCase(repository);
    }

    @Bean
    public GetLlmVendorByIdUseCase getLlmVendorByIdUseCase(LlmVendorRepository repository) {
        return new GetLlmVendorByIdUseCase(repository);
    }

    @Bean
    public ListLlmVendorUseCase listLlmVendorUseCase(LlmVendorRepository repository) {
        return new ListLlmVendorUseCase(repository);
    }

    @Bean
    public UpdateLlmVendorUseCase updateLlmVendorUseCase(LlmVendorRepository repository) {
        return new UpdateLlmVendorUseCase(repository);
    }

    @Bean
    public DeleteLlmVendorUseCase deleteLlmVendorUseCase(LlmVendorRepository repository) {
        return new DeleteLlmVendorUseCase(repository);
    }
}
