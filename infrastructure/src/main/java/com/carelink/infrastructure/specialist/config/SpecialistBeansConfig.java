package com.carelink.infrastructure.specialist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.specialist.usecase.*;
import com.carelink.domain.specialist.port.repository.SpecialistRepository;
import com.carelink.infrastructure.specialist.adapters.out.persistence.mappers.SpecialistPersistenceMapper;
import com.carelink.infrastructure.specialist.adapters.out.persistence.repositories.SpecialistJpaRepository;
import com.carelink.infrastructure.specialist.adapters.out.persistence.repositories.SpecialistRepositoryAdapter;

@Configuration
public class SpecialistBeansConfig {
    @Bean
    public SpecialistPersistenceMapper specialistPersistenceMapper() {
        return new SpecialistPersistenceMapper();
    }

    @Bean
    public SpecialistRepository specialistRepository(SpecialistJpaRepository repository, SpecialistPersistenceMapper mapper) {
        return new SpecialistRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterSpecialistUseCase registerSpecialistUseCase(SpecialistRepository repository) {
        return new RegisterSpecialistUseCase(repository);
    }

    @Bean
    public GetSpecialistByIdUseCase getSpecialistByIdUseCase(SpecialistRepository repository) {
        return new GetSpecialistByIdUseCase(repository);
    }

    @Bean
    public ListSpecialistUseCase listSpecialistUseCase(SpecialistRepository repository) {
        return new ListSpecialistUseCase(repository);
    }

    @Bean
    public UpdateSpecialistUseCase updateSpecialistUseCase(SpecialistRepository repository) {
        return new UpdateSpecialistUseCase(repository);
    }

    @Bean
    public DeleteSpecialistUseCase deleteSpecialistUseCase(SpecialistRepository repository) {
        return new DeleteSpecialistUseCase(repository);
    }
}
