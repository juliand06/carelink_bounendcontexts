package com.carelink.infrastructure.healthrecord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.healthrecord.usecase.*;
import com.carelink.domain.healthrecord.port.repository.HealthRecordRepository;
import com.carelink.infrastructure.healthrecord.adapters.out.persistence.mappers.HealthRecordPersistenceMapper;
import com.carelink.infrastructure.healthrecord.adapters.out.persistence.repositories.HealthRecordJpaRepository;
import com.carelink.infrastructure.healthrecord.adapters.out.persistence.repositories.HealthRecordRepositoryAdapter;

@Configuration
public class HealthRecordBeansConfig {
    @Bean
    public HealthRecordPersistenceMapper healthRecordPersistenceMapper() {
        return new HealthRecordPersistenceMapper();
    }

    @Bean
    public HealthRecordRepository healthRecordRepository(HealthRecordJpaRepository repository, HealthRecordPersistenceMapper mapper) {
        return new HealthRecordRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterHealthRecordUseCase registerHealthRecordUseCase(HealthRecordRepository repository) {
        return new RegisterHealthRecordUseCase(repository);
    }

    @Bean
    public GetHealthRecordByIdUseCase getHealthRecordByIdUseCase(HealthRecordRepository repository) {
        return new GetHealthRecordByIdUseCase(repository);
    }

    @Bean
    public ListHealthRecordUseCase listHealthRecordUseCase(HealthRecordRepository repository) {
        return new ListHealthRecordUseCase(repository);
    }

    @Bean
    public UpdateHealthRecordUseCase updateHealthRecordUseCase(HealthRecordRepository repository) {
        return new UpdateHealthRecordUseCase(repository);
    }

    @Bean
    public DeleteHealthRecordUseCase deleteHealthRecordUseCase(HealthRecordRepository repository) {
        return new DeleteHealthRecordUseCase(repository);
    }
}
