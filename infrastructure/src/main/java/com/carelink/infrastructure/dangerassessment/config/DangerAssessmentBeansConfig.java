package com.carelink.infrastructure.dangerassessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.dangerassessment.usecase.*;
import com.carelink.domain.dangerassessment.port.repository.DangerAssessmentRepository;
import com.carelink.infrastructure.dangerassessment.adapters.out.persistence.mappers.DangerAssessmentPersistenceMapper;
import com.carelink.infrastructure.dangerassessment.adapters.out.persistence.repositories.DangerAssessmentJpaRepository;
import com.carelink.infrastructure.dangerassessment.adapters.out.persistence.repositories.DangerAssessmentRepositoryAdapter;

@Configuration
public class DangerAssessmentBeansConfig {
    @Bean
    public DangerAssessmentPersistenceMapper dangerAssessmentPersistenceMapper() {
        return new DangerAssessmentPersistenceMapper();
    }

    @Bean
    public DangerAssessmentRepository dangerAssessmentRepository(DangerAssessmentJpaRepository repository, DangerAssessmentPersistenceMapper mapper) {
        return new DangerAssessmentRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterDangerAssessmentUseCase registerDangerAssessmentUseCase(DangerAssessmentRepository repository) {
        return new RegisterDangerAssessmentUseCase(repository);
    }

    @Bean
    public GetDangerAssessmentByIdUseCase getDangerAssessmentByIdUseCase(DangerAssessmentRepository repository) {
        return new GetDangerAssessmentByIdUseCase(repository);
    }

    @Bean
    public ListDangerAssessmentUseCase listDangerAssessmentUseCase(DangerAssessmentRepository repository) {
        return new ListDangerAssessmentUseCase(repository);
    }

    @Bean
    public UpdateDangerAssessmentUseCase updateDangerAssessmentUseCase(DangerAssessmentRepository repository) {
        return new UpdateDangerAssessmentUseCase(repository);
    }

    @Bean
    public DeleteDangerAssessmentUseCase deleteDangerAssessmentUseCase(DangerAssessmentRepository repository) {
        return new DeleteDangerAssessmentUseCase(repository);
    }
}
