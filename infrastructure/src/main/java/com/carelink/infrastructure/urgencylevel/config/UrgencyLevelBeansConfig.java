package com.carelink.infrastructure.urgencylevel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.urgencylevel.usecase.*;
import com.carelink.domain.urgencylevel.port.repository.UrgencyLevelRepository;
import com.carelink.infrastructure.urgencylevel.adapters.out.persistence.mappers.UrgencyLevelPersistenceMapper;
import com.carelink.infrastructure.urgencylevel.adapters.out.persistence.repositories.UrgencyLevelJpaRepository;
import com.carelink.infrastructure.urgencylevel.adapters.out.persistence.repositories.UrgencyLevelRepositoryAdapter;

@Configuration
public class UrgencyLevelBeansConfig {
    @Bean
    public UrgencyLevelPersistenceMapper urgencyLevelPersistenceMapper() {
        return new UrgencyLevelPersistenceMapper();
    }

    @Bean
    public UrgencyLevelRepository urgencyLevelRepository(UrgencyLevelJpaRepository repository, UrgencyLevelPersistenceMapper mapper) {
        return new UrgencyLevelRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterUrgencyLevelUseCase registerUrgencyLevelUseCase(UrgencyLevelRepository repository) {
        return new RegisterUrgencyLevelUseCase(repository);
    }

    @Bean
    public GetUrgencyLevelByIdUseCase getUrgencyLevelByIdUseCase(UrgencyLevelRepository repository) {
        return new GetUrgencyLevelByIdUseCase(repository);
    }

    @Bean
    public ListUrgencyLevelUseCase listUrgencyLevelUseCase(UrgencyLevelRepository repository) {
        return new ListUrgencyLevelUseCase(repository);
    }

    @Bean
    public UpdateUrgencyLevelUseCase updateUrgencyLevelUseCase(UrgencyLevelRepository repository) {
        return new UpdateUrgencyLevelUseCase(repository);
    }

    @Bean
    public DeleteUrgencyLevelUseCase deleteUrgencyLevelUseCase(UrgencyLevelRepository repository) {
        return new DeleteUrgencyLevelUseCase(repository);
    }
}
