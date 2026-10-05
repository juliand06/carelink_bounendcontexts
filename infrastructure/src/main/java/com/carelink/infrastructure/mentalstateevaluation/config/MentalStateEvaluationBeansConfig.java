package com.carelink.infrastructure.mentalstateevaluation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.mentalstateevaluation.usecase.*;
import com.carelink.domain.mentalstateevaluation.port.repository.MentalStateEvaluationRepository;
import com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.mappers.MentalStateEvaluationPersistenceMapper;
import com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.repositories.MentalStateEvaluationJpaRepository;
import com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.repositories.MentalStateEvaluationRepositoryAdapter;

@Configuration
public class MentalStateEvaluationBeansConfig {
    @Bean
    public MentalStateEvaluationPersistenceMapper mentalStateEvaluationPersistenceMapper() {
        return new MentalStateEvaluationPersistenceMapper();
    }

    @Bean
    public MentalStateEvaluationRepository mentalStateEvaluationRepository(MentalStateEvaluationJpaRepository repository, MentalStateEvaluationPersistenceMapper mapper) {
        return new MentalStateEvaluationRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterMentalStateEvaluationUseCase registerMentalStateEvaluationUseCase(MentalStateEvaluationRepository repository) {
        return new RegisterMentalStateEvaluationUseCase(repository);
    }

    @Bean
    public GetMentalStateEvaluationByIdUseCase getMentalStateEvaluationByIdUseCase(MentalStateEvaluationRepository repository) {
        return new GetMentalStateEvaluationByIdUseCase(repository);
    }

    @Bean
    public ListMentalStateEvaluationUseCase listMentalStateEvaluationUseCase(MentalStateEvaluationRepository repository) {
        return new ListMentalStateEvaluationUseCase(repository);
    }

    @Bean
    public UpdateMentalStateEvaluationUseCase updateMentalStateEvaluationUseCase(MentalStateEvaluationRepository repository) {
        return new UpdateMentalStateEvaluationUseCase(repository);
    }

    @Bean
    public DeleteMentalStateEvaluationUseCase deleteMentalStateEvaluationUseCase(MentalStateEvaluationRepository repository) {
        return new DeleteMentalStateEvaluationUseCase(repository);
    }
}
