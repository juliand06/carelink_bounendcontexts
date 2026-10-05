package com.carelink.infrastructure.dangerlevel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.dangerlevel.usecase.*;
import com.carelink.domain.dangerlevel.port.repository.DangerLevelRepository;
import com.carelink.infrastructure.dangerlevel.adapters.out.persistence.mappers.DangerLevelPersistenceMapper;
import com.carelink.infrastructure.dangerlevel.adapters.out.persistence.repositories.DangerLevelJpaRepository;
import com.carelink.infrastructure.dangerlevel.adapters.out.persistence.repositories.DangerLevelRepositoryAdapter;

@Configuration
public class DangerLevelBeansConfig {
    @Bean
    public DangerLevelPersistenceMapper dangerLevelPersistenceMapper() {
        return new DangerLevelPersistenceMapper();
    }

    @Bean
    public DangerLevelRepository dangerLevelRepository(DangerLevelJpaRepository repository, DangerLevelPersistenceMapper mapper) {
        return new DangerLevelRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterDangerLevelUseCase registerDangerLevelUseCase(DangerLevelRepository repository) {
        return new RegisterDangerLevelUseCase(repository);
    }

    @Bean
    public GetDangerLevelByIdUseCase getDangerLevelByIdUseCase(DangerLevelRepository repository) {
        return new GetDangerLevelByIdUseCase(repository);
    }

    @Bean
    public ListDangerLevelUseCase listDangerLevelUseCase(DangerLevelRepository repository) {
        return new ListDangerLevelUseCase(repository);
    }

    @Bean
    public UpdateDangerLevelUseCase updateDangerLevelUseCase(DangerLevelRepository repository) {
        return new UpdateDangerLevelUseCase(repository);
    }

    @Bean
    public DeleteDangerLevelUseCase deleteDangerLevelUseCase(DangerLevelRepository repository) {
        return new DeleteDangerLevelUseCase(repository);
    }
}
