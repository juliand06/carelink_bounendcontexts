package com.carelink.infrastructure.iddocumenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.iddocumenttype.usecase.*;
import com.carelink.domain.iddocumenttype.port.repository.IdDocumentTypeRepository;
import com.carelink.infrastructure.iddocumenttype.adapters.out.persistence.mappers.IdDocumentTypePersistenceMapper;
import com.carelink.infrastructure.iddocumenttype.adapters.out.persistence.repositories.IdDocumentTypeJpaRepository;
import com.carelink.infrastructure.iddocumenttype.adapters.out.persistence.repositories.IdDocumentTypeRepositoryAdapter;

@Configuration
public class IdDocumentTypeBeansConfig {
    @Bean
    public IdDocumentTypePersistenceMapper idDocumentTypePersistenceMapper() {
        return new IdDocumentTypePersistenceMapper();
    }

    @Bean
    public IdDocumentTypeRepository idDocumentTypeRepository(IdDocumentTypeJpaRepository repository, IdDocumentTypePersistenceMapper mapper) {
        return new IdDocumentTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterIdDocumentTypeUseCase registerIdDocumentTypeUseCase(IdDocumentTypeRepository repository) {
        return new RegisterIdDocumentTypeUseCase(repository);
    }

    @Bean
    public GetIdDocumentTypeByIdUseCase getIdDocumentTypeByIdUseCase(IdDocumentTypeRepository repository) {
        return new GetIdDocumentTypeByIdUseCase(repository);
    }

    @Bean
    public ListIdDocumentTypeUseCase listIdDocumentTypeUseCase(IdDocumentTypeRepository repository) {
        return new ListIdDocumentTypeUseCase(repository);
    }

    @Bean
    public UpdateIdDocumentTypeUseCase updateIdDocumentTypeUseCase(IdDocumentTypeRepository repository) {
        return new UpdateIdDocumentTypeUseCase(repository);
    }

    @Bean
    public DeleteIdDocumentTypeUseCase deleteIdDocumentTypeUseCase(IdDocumentTypeRepository repository) {
        return new DeleteIdDocumentTypeUseCase(repository);
    }
}
