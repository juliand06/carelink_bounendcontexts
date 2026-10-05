package com.carelink.infrastructure.handoffassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.handoffassignment.usecase.*;
import com.carelink.domain.handoffassignment.port.repository.HandoffAssignmentRepository;
import com.carelink.infrastructure.handoffassignment.adapters.out.persistence.mappers.HandoffAssignmentPersistenceMapper;
import com.carelink.infrastructure.handoffassignment.adapters.out.persistence.repositories.HandoffAssignmentJpaRepository;
import com.carelink.infrastructure.handoffassignment.adapters.out.persistence.repositories.HandoffAssignmentRepositoryAdapter;

@Configuration
public class HandoffAssignmentBeansConfig {
    @Bean
    public HandoffAssignmentPersistenceMapper handoffAssignmentPersistenceMapper() {
        return new HandoffAssignmentPersistenceMapper();
    }

    @Bean
    public HandoffAssignmentRepository handoffAssignmentRepository(HandoffAssignmentJpaRepository repository, HandoffAssignmentPersistenceMapper mapper) {
        return new HandoffAssignmentRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterHandoffAssignmentUseCase registerHandoffAssignmentUseCase(HandoffAssignmentRepository repository) {
        return new RegisterHandoffAssignmentUseCase(repository);
    }

    @Bean
    public GetHandoffAssignmentByIdUseCase getHandoffAssignmentByIdUseCase(HandoffAssignmentRepository repository) {
        return new GetHandoffAssignmentByIdUseCase(repository);
    }

    @Bean
    public ListHandoffAssignmentUseCase listHandoffAssignmentUseCase(HandoffAssignmentRepository repository) {
        return new ListHandoffAssignmentUseCase(repository);
    }

    @Bean
    public UpdateHandoffAssignmentUseCase updateHandoffAssignmentUseCase(HandoffAssignmentRepository repository) {
        return new UpdateHandoffAssignmentUseCase(repository);
    }

    @Bean
    public DeleteHandoffAssignmentUseCase deleteHandoffAssignmentUseCase(HandoffAssignmentRepository repository) {
        return new DeleteHandoffAssignmentUseCase(repository);
    }
}
