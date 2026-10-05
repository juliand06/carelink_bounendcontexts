package com.carelink.infrastructure.caregoal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.caregoal.usecase.*;
import com.carelink.domain.caregoal.port.repository.CareGoalRepository;
import com.carelink.infrastructure.caregoal.adapters.out.persistence.mappers.CareGoalPersistenceMapper;
import com.carelink.infrastructure.caregoal.adapters.out.persistence.repositories.CareGoalJpaRepository;
import com.carelink.infrastructure.caregoal.adapters.out.persistence.repositories.CareGoalRepositoryAdapter;

@Configuration
public class CareGoalBeansConfig {
    @Bean
    public CareGoalPersistenceMapper careGoalPersistenceMapper() {
        return new CareGoalPersistenceMapper();
    }

    @Bean
    public CareGoalRepository careGoalRepository(CareGoalJpaRepository repository, CareGoalPersistenceMapper mapper) {
        return new CareGoalRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterCareGoalUseCase registerCareGoalUseCase(CareGoalRepository repository) {
        return new RegisterCareGoalUseCase(repository);
    }

    @Bean
    public GetCareGoalByIdUseCase getCareGoalByIdUseCase(CareGoalRepository repository) {
        return new GetCareGoalByIdUseCase(repository);
    }

    @Bean
    public ListCareGoalUseCase listCareGoalUseCase(CareGoalRepository repository) {
        return new ListCareGoalUseCase(repository);
    }

    @Bean
    public UpdateCareGoalUseCase updateCareGoalUseCase(CareGoalRepository repository) {
        return new UpdateCareGoalUseCase(repository);
    }

    @Bean
    public DeleteCareGoalUseCase deleteCareGoalUseCase(CareGoalRepository repository) {
        return new DeleteCareGoalUseCase(repository);
    }
}
