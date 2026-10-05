package com.carelink.infrastructure.handoff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.handoff.usecase.*;
import com.carelink.domain.handoff.port.repository.HandoffRepository;
import com.carelink.infrastructure.handoff.adapters.out.persistence.mappers.HandoffPersistenceMapper;
import com.carelink.infrastructure.handoff.adapters.out.persistence.repositories.HandoffJpaRepository;
import com.carelink.infrastructure.handoff.adapters.out.persistence.repositories.HandoffRepositoryAdapter;

@Configuration
public class HandoffBeansConfig {
    @Bean
    public HandoffPersistenceMapper handoffPersistenceMapper() {
        return new HandoffPersistenceMapper();
    }

    @Bean
    public HandoffRepository handoffRepository(HandoffJpaRepository repository, HandoffPersistenceMapper mapper) {
        return new HandoffRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterHandoffUseCase registerHandoffUseCase(HandoffRepository repository) {
        return new RegisterHandoffUseCase(repository);
    }

    @Bean
    public GetHandoffByIdUseCase getHandoffByIdUseCase(HandoffRepository repository) {
        return new GetHandoffByIdUseCase(repository);
    }

    @Bean
    public ListHandoffUseCase listHandoffUseCase(HandoffRepository repository) {
        return new ListHandoffUseCase(repository);
    }

    @Bean
    public UpdateHandoffUseCase updateHandoffUseCase(HandoffRepository repository) {
        return new UpdateHandoffUseCase(repository);
    }

    @Bean
    public DeleteHandoffUseCase deleteHandoffUseCase(HandoffRepository repository) {
        return new DeleteHandoffUseCase(repository);
    }
}
