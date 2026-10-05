package com.carelink.infrastructure.classificationsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.classificationsystem.usecase.*;
import com.carelink.domain.classificationsystem.port.repository.ClassificationSystemRepository;
import com.carelink.infrastructure.classificationsystem.adapters.out.persistence.mappers.ClassificationSystemPersistenceMapper;
import com.carelink.infrastructure.classificationsystem.adapters.out.persistence.repositories.ClassificationSystemJpaRepository;
import com.carelink.infrastructure.classificationsystem.adapters.out.persistence.repositories.ClassificationSystemRepositoryAdapter;

@Configuration
public class ClassificationSystemBeansConfig {
    @Bean
    public ClassificationSystemPersistenceMapper classificationSystemPersistenceMapper() {
        return new ClassificationSystemPersistenceMapper();
    }

    @Bean
    public ClassificationSystemRepository classificationSystemRepository(ClassificationSystemJpaRepository repository, ClassificationSystemPersistenceMapper mapper) {
        return new ClassificationSystemRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterClassificationSystemUseCase registerClassificationSystemUseCase(ClassificationSystemRepository repository) {
        return new RegisterClassificationSystemUseCase(repository);
    }

    @Bean
    public GetClassificationSystemByIdUseCase getClassificationSystemByIdUseCase(ClassificationSystemRepository repository) {
        return new GetClassificationSystemByIdUseCase(repository);
    }

    @Bean
    public ListClassificationSystemUseCase listClassificationSystemUseCase(ClassificationSystemRepository repository) {
        return new ListClassificationSystemUseCase(repository);
    }

    @Bean
    public UpdateClassificationSystemUseCase updateClassificationSystemUseCase(ClassificationSystemRepository repository) {
        return new UpdateClassificationSystemUseCase(repository);
    }

    @Bean
    public DeleteClassificationSystemUseCase deleteClassificationSystemUseCase(ClassificationSystemRepository repository) {
        return new DeleteClassificationSystemUseCase(repository);
    }
}
