package com.carelink.infrastructure.healthrecordstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.healthrecordstatus.usecase.*;
import com.carelink.domain.healthrecordstatus.port.repository.HealthRecordStatusRepository;
import com.carelink.infrastructure.healthrecordstatus.adapters.out.persistence.mappers.HealthRecordStatusPersistenceMapper;
import com.carelink.infrastructure.healthrecordstatus.adapters.out.persistence.repositories.HealthRecordStatusJpaRepository;
import com.carelink.infrastructure.healthrecordstatus.adapters.out.persistence.repositories.HealthRecordStatusRepositoryAdapter;

@Configuration
public class HealthRecordStatusBeansConfig {
    @Bean
    public HealthRecordStatusPersistenceMapper healthRecordStatusPersistenceMapper() {
        return new HealthRecordStatusPersistenceMapper();
    }

    @Bean
    public HealthRecordStatusRepository healthRecordStatusRepository(HealthRecordStatusJpaRepository repository, HealthRecordStatusPersistenceMapper mapper) {
        return new HealthRecordStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterHealthRecordStatusUseCase registerHealthRecordStatusUseCase(HealthRecordStatusRepository repository) {
        return new RegisterHealthRecordStatusUseCase(repository);
    }

    @Bean
    public GetHealthRecordStatusByIdUseCase getHealthRecordStatusByIdUseCase(HealthRecordStatusRepository repository) {
        return new GetHealthRecordStatusByIdUseCase(repository);
    }

    @Bean
    public ListHealthRecordStatusUseCase listHealthRecordStatusUseCase(HealthRecordStatusRepository repository) {
        return new ListHealthRecordStatusUseCase(repository);
    }

    @Bean
    public UpdateHealthRecordStatusUseCase updateHealthRecordStatusUseCase(HealthRecordStatusRepository repository) {
        return new UpdateHealthRecordStatusUseCase(repository);
    }

    @Bean
    public DeleteHealthRecordStatusUseCase deleteHealthRecordStatusUseCase(HealthRecordStatusRepository repository) {
        return new DeleteHealthRecordStatusUseCase(repository);
    }
}
