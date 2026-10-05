package com.carelink.infrastructure.evaluationinstrumenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.evaluationinstrumenttype.usecase.*;
import com.carelink.domain.evaluationinstrumenttype.port.repository.EvaluationInstrumentTypeRepository;
import com.carelink.infrastructure.evaluationinstrumenttype.adapters.out.persistence.mappers.EvaluationInstrumentTypePersistenceMapper;
import com.carelink.infrastructure.evaluationinstrumenttype.adapters.out.persistence.repositories.EvaluationInstrumentTypeJpaRepository;
import com.carelink.infrastructure.evaluationinstrumenttype.adapters.out.persistence.repositories.EvaluationInstrumentTypeRepositoryAdapter;

@Configuration
public class EvaluationInstrumentTypeBeansConfig {
    @Bean
    public EvaluationInstrumentTypePersistenceMapper evaluationInstrumentTypePersistenceMapper() {
        return new EvaluationInstrumentTypePersistenceMapper();
    }

    @Bean
    public EvaluationInstrumentTypeRepository evaluationInstrumentTypeRepository(EvaluationInstrumentTypeJpaRepository repository, EvaluationInstrumentTypePersistenceMapper mapper) {
        return new EvaluationInstrumentTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterEvaluationInstrumentTypeUseCase registerEvaluationInstrumentTypeUseCase(EvaluationInstrumentTypeRepository repository) {
        return new RegisterEvaluationInstrumentTypeUseCase(repository);
    }

    @Bean
    public GetEvaluationInstrumentTypeByIdUseCase getEvaluationInstrumentTypeByIdUseCase(EvaluationInstrumentTypeRepository repository) {
        return new GetEvaluationInstrumentTypeByIdUseCase(repository);
    }

    @Bean
    public ListEvaluationInstrumentTypeUseCase listEvaluationInstrumentTypeUseCase(EvaluationInstrumentTypeRepository repository) {
        return new ListEvaluationInstrumentTypeUseCase(repository);
    }

    @Bean
    public UpdateEvaluationInstrumentTypeUseCase updateEvaluationInstrumentTypeUseCase(EvaluationInstrumentTypeRepository repository) {
        return new UpdateEvaluationInstrumentTypeUseCase(repository);
    }

    @Bean
    public DeleteEvaluationInstrumentTypeUseCase deleteEvaluationInstrumentTypeUseCase(EvaluationInstrumentTypeRepository repository) {
        return new DeleteEvaluationInstrumentTypeUseCase(repository);
    }
}
