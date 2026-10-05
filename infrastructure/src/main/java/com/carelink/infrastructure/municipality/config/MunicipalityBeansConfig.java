package com.carelink.infrastructure.municipality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.municipality.usecase.*;
import com.carelink.domain.municipality.port.repository.MunicipalityRepository;
import com.carelink.infrastructure.municipality.adapters.out.persistence.mappers.MunicipalityPersistenceMapper;
import com.carelink.infrastructure.municipality.adapters.out.persistence.repositories.MunicipalityJpaRepository;
import com.carelink.infrastructure.municipality.adapters.out.persistence.repositories.MunicipalityRepositoryAdapter;

@Configuration
public class MunicipalityBeansConfig {
    @Bean
    public MunicipalityPersistenceMapper municipalityPersistenceMapper() {
        return new MunicipalityPersistenceMapper();
    }

    @Bean
    public MunicipalityRepository municipalityRepository(MunicipalityJpaRepository repository, MunicipalityPersistenceMapper mapper) {
        return new MunicipalityRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterMunicipalityUseCase registerMunicipalityUseCase(MunicipalityRepository repository) {
        return new RegisterMunicipalityUseCase(repository);
    }

    @Bean
    public GetMunicipalityByIdUseCase getMunicipalityByIdUseCase(MunicipalityRepository repository) {
        return new GetMunicipalityByIdUseCase(repository);
    }

    @Bean
    public ListMunicipalityUseCase listMunicipalityUseCase(MunicipalityRepository repository) {
        return new ListMunicipalityUseCase(repository);
    }

    @Bean
    public UpdateMunicipalityUseCase updateMunicipalityUseCase(MunicipalityRepository repository) {
        return new UpdateMunicipalityUseCase(repository);
    }

    @Bean
    public DeleteMunicipalityUseCase deleteMunicipalityUseCase(MunicipalityRepository repository) {
        return new DeleteMunicipalityUseCase(repository);
    }
}
