package com.carelink.infrastructure.academicprogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.academicprogram.usecase.*;
import com.carelink.domain.academicprogram.port.repository.AcademicProgramRepository;
import com.carelink.infrastructure.academicprogram.adapters.out.persistence.mappers.AcademicProgramPersistenceMapper;
import com.carelink.infrastructure.academicprogram.adapters.out.persistence.repositories.AcademicProgramJpaRepository;
import com.carelink.infrastructure.academicprogram.adapters.out.persistence.repositories.AcademicProgramRepositoryAdapter;

@Configuration
public class AcademicProgramBeansConfig {
    @Bean
    public AcademicProgramPersistenceMapper academicProgramPersistenceMapper() {
        return new AcademicProgramPersistenceMapper();
    }

    @Bean
    public AcademicProgramRepository academicProgramRepository(AcademicProgramJpaRepository repository, AcademicProgramPersistenceMapper mapper) {
        return new AcademicProgramRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterAcademicProgramUseCase registerAcademicProgramUseCase(AcademicProgramRepository repository) {
        return new RegisterAcademicProgramUseCase(repository);
    }

    @Bean
    public GetAcademicProgramByIdUseCase getAcademicProgramByIdUseCase(AcademicProgramRepository repository) {
        return new GetAcademicProgramByIdUseCase(repository);
    }

    @Bean
    public ListAcademicProgramUseCase listAcademicProgramUseCase(AcademicProgramRepository repository) {
        return new ListAcademicProgramUseCase(repository);
    }

    @Bean
    public UpdateAcademicProgramUseCase updateAcademicProgramUseCase(AcademicProgramRepository repository) {
        return new UpdateAcademicProgramUseCase(repository);
    }

    @Bean
    public DeleteAcademicProgramUseCase deleteAcademicProgramUseCase(AcademicProgramRepository repository) {
        return new DeleteAcademicProgramUseCase(repository);
    }
}
