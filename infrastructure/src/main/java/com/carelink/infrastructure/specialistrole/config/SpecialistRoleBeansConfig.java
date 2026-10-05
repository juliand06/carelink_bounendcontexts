package com.carelink.infrastructure.specialistrole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.specialistrole.usecase.*;
import com.carelink.domain.specialistrole.port.repository.SpecialistRoleRepository;
import com.carelink.infrastructure.specialistrole.adapters.out.persistence.mappers.SpecialistRolePersistenceMapper;
import com.carelink.infrastructure.specialistrole.adapters.out.persistence.repositories.SpecialistRoleJpaRepository;
import com.carelink.infrastructure.specialistrole.adapters.out.persistence.repositories.SpecialistRoleRepositoryAdapter;

@Configuration
public class SpecialistRoleBeansConfig {
    @Bean
    public SpecialistRolePersistenceMapper specialistRolePersistenceMapper() {
        return new SpecialistRolePersistenceMapper();
    }

    @Bean
    public SpecialistRoleRepository specialistRoleRepository(SpecialistRoleJpaRepository repository, SpecialistRolePersistenceMapper mapper) {
        return new SpecialistRoleRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterSpecialistRoleUseCase registerSpecialistRoleUseCase(SpecialistRoleRepository repository) {
        return new RegisterSpecialistRoleUseCase(repository);
    }

    @Bean
    public GetSpecialistRoleByIdUseCase getSpecialistRoleByIdUseCase(SpecialistRoleRepository repository) {
        return new GetSpecialistRoleByIdUseCase(repository);
    }

    @Bean
    public ListSpecialistRoleUseCase listSpecialistRoleUseCase(SpecialistRoleRepository repository) {
        return new ListSpecialistRoleUseCase(repository);
    }

    @Bean
    public UpdateSpecialistRoleUseCase updateSpecialistRoleUseCase(SpecialistRoleRepository repository) {
        return new UpdateSpecialistRoleUseCase(repository);
    }

    @Bean
    public DeleteSpecialistRoleUseCase deleteSpecialistRoleUseCase(SpecialistRoleRepository repository) {
        return new DeleteSpecialistRoleUseCase(repository);
    }
}
