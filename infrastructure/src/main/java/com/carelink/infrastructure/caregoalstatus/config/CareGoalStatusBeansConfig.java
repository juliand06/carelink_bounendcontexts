package com.carelink.infrastructure.caregoalstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.caregoalstatus.usecase.*;
import com.carelink.domain.caregoalstatus.port.repository.CareGoalStatusRepository;
import com.carelink.infrastructure.caregoalstatus.adapters.out.persistence.mappers.CareGoalStatusPersistenceMapper;
import com.carelink.infrastructure.caregoalstatus.adapters.out.persistence.repositories.CareGoalStatusJpaRepository;
import com.carelink.infrastructure.caregoalstatus.adapters.out.persistence.repositories.CareGoalStatusRepositoryAdapter;

@Configuration
public class CareGoalStatusBeansConfig {
    @Bean
    public CareGoalStatusPersistenceMapper careGoalStatusPersistenceMapper() {
        return new CareGoalStatusPersistenceMapper();
    }

    @Bean
    public CareGoalStatusRepository careGoalStatusRepository(CareGoalStatusJpaRepository repository, CareGoalStatusPersistenceMapper mapper) {
        return new CareGoalStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterCareGoalStatusUseCase registerCareGoalStatusUseCase(CareGoalStatusRepository repository) {
        return new RegisterCareGoalStatusUseCase(repository);
    }

    @Bean
    public GetCareGoalStatusByIdUseCase getCareGoalStatusByIdUseCase(CareGoalStatusRepository repository) {
        return new GetCareGoalStatusByIdUseCase(repository);
    }

    @Bean
    public ListCareGoalStatusUseCase listCareGoalStatusUseCase(CareGoalStatusRepository repository) {
        return new ListCareGoalStatusUseCase(repository);
    }

    @Bean
    public UpdateCareGoalStatusUseCase updateCareGoalStatusUseCase(CareGoalStatusRepository repository) {
        return new UpdateCareGoalStatusUseCase(repository);
    }

    @Bean
    public DeleteCareGoalStatusUseCase deleteCareGoalStatusUseCase(CareGoalStatusRepository repository) {
        return new DeleteCareGoalStatusUseCase(repository);
    }
}
