package com.carelink.infrastructure.handoffstatusaudit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.handoffstatusaudit.usecase.*;
import com.carelink.domain.handoffstatusaudit.port.repository.HandoffStatusAuditRepository;
import com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.mappers.HandoffStatusAuditPersistenceMapper;
import com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.repositories.HandoffStatusAuditJpaRepository;
import com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.repositories.HandoffStatusAuditRepositoryAdapter;

@Configuration
public class HandoffStatusAuditBeansConfig {
    @Bean
    public HandoffStatusAuditPersistenceMapper handoffStatusAuditPersistenceMapper() {
        return new HandoffStatusAuditPersistenceMapper();
    }

    @Bean
    public HandoffStatusAuditRepository handoffStatusAuditRepository(HandoffStatusAuditJpaRepository repository, HandoffStatusAuditPersistenceMapper mapper) {
        return new HandoffStatusAuditRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterHandoffStatusAuditUseCase registerHandoffStatusAuditUseCase(HandoffStatusAuditRepository repository) {
        return new RegisterHandoffStatusAuditUseCase(repository);
    }

    @Bean
    public GetHandoffStatusAuditByIdUseCase getHandoffStatusAuditByIdUseCase(HandoffStatusAuditRepository repository) {
        return new GetHandoffStatusAuditByIdUseCase(repository);
    }

    @Bean
    public ListHandoffStatusAuditUseCase listHandoffStatusAuditUseCase(HandoffStatusAuditRepository repository) {
        return new ListHandoffStatusAuditUseCase(repository);
    }

    @Bean
    public UpdateHandoffStatusAuditUseCase updateHandoffStatusAuditUseCase(HandoffStatusAuditRepository repository) {
        return new UpdateHandoffStatusAuditUseCase(repository);
    }

    @Bean
    public DeleteHandoffStatusAuditUseCase deleteHandoffStatusAuditUseCase(HandoffStatusAuditRepository repository) {
        return new DeleteHandoffStatusAuditUseCase(repository);
    }
}
