package com.carelink.infrastructure.specialistcredential.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.specialistcredential.usecase.*;
import com.carelink.domain.specialistcredential.port.repository.SpecialistCredentialRepository;
import com.carelink.infrastructure.specialistcredential.adapters.out.persistence.mappers.SpecialistCredentialPersistenceMapper;
import com.carelink.infrastructure.specialistcredential.adapters.out.persistence.repositories.SpecialistCredentialJpaRepository;
import com.carelink.infrastructure.specialistcredential.adapters.out.persistence.repositories.SpecialistCredentialRepositoryAdapter;

@Configuration
public class SpecialistCredentialBeansConfig {
    @Bean
    public SpecialistCredentialPersistenceMapper specialistCredentialPersistenceMapper() {
        return new SpecialistCredentialPersistenceMapper();
    }

    @Bean
    public SpecialistCredentialRepository specialistCredentialRepository(SpecialistCredentialJpaRepository repository, SpecialistCredentialPersistenceMapper mapper) {
        return new SpecialistCredentialRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterSpecialistCredentialUseCase registerSpecialistCredentialUseCase(SpecialistCredentialRepository repository) {
        return new RegisterSpecialistCredentialUseCase(repository);
    }

    @Bean
    public GetSpecialistCredentialByIdUseCase getSpecialistCredentialByIdUseCase(SpecialistCredentialRepository repository) {
        return new GetSpecialistCredentialByIdUseCase(repository);
    }

    @Bean
    public ListSpecialistCredentialUseCase listSpecialistCredentialUseCase(SpecialistCredentialRepository repository) {
        return new ListSpecialistCredentialUseCase(repository);
    }

    @Bean
    public UpdateSpecialistCredentialUseCase updateSpecialistCredentialUseCase(SpecialistCredentialRepository repository) {
        return new UpdateSpecialistCredentialUseCase(repository);
    }

    @Bean
    public DeleteSpecialistCredentialUseCase deleteSpecialistCredentialUseCase(SpecialistCredentialRepository repository) {
        return new DeleteSpecialistCredentialUseCase(repository);
    }
}
