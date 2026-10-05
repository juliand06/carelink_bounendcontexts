package com.carelink.infrastructure.handoffstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.handoffstatus.usecase.*;
import com.carelink.domain.handoffstatus.port.repository.HandoffStatusRepository;
import com.carelink.infrastructure.handoffstatus.adapters.out.persistence.mappers.HandoffStatusPersistenceMapper;
import com.carelink.infrastructure.handoffstatus.adapters.out.persistence.repositories.HandoffStatusJpaRepository;
import com.carelink.infrastructure.handoffstatus.adapters.out.persistence.repositories.HandoffStatusRepositoryAdapter;

@Configuration
public class HandoffStatusBeansConfig {
    @Bean
    public HandoffStatusPersistenceMapper handoffStatusPersistenceMapper() {
        return new HandoffStatusPersistenceMapper();
    }

    @Bean
    public HandoffStatusRepository handoffStatusRepository(HandoffStatusJpaRepository repository, HandoffStatusPersistenceMapper mapper) {
        return new HandoffStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterHandoffStatusUseCase registerHandoffStatusUseCase(HandoffStatusRepository repository) {
        return new RegisterHandoffStatusUseCase(repository);
    }

    @Bean
    public GetHandoffStatusByIdUseCase getHandoffStatusByIdUseCase(HandoffStatusRepository repository) {
        return new GetHandoffStatusByIdUseCase(repository);
    }

    @Bean
    public ListHandoffStatusUseCase listHandoffStatusUseCase(HandoffStatusRepository repository) {
        return new ListHandoffStatusUseCase(repository);
    }

    @Bean
    public UpdateHandoffStatusUseCase updateHandoffStatusUseCase(HandoffStatusRepository repository) {
        return new UpdateHandoffStatusUseCase(repository);
    }

    @Bean
    public DeleteHandoffStatusUseCase deleteHandoffStatusUseCase(HandoffStatusRepository repository) {
        return new DeleteHandoffStatusUseCase(repository);
    }
}
