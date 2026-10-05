package com.carelink.infrastructure.informedconsenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.informedconsenttype.usecase.*;
import com.carelink.domain.informedconsenttype.port.repository.InformedConsentTypeRepository;
import com.carelink.infrastructure.informedconsenttype.adapters.out.persistence.mappers.InformedConsentTypePersistenceMapper;
import com.carelink.infrastructure.informedconsenttype.adapters.out.persistence.repositories.InformedConsentTypeJpaRepository;
import com.carelink.infrastructure.informedconsenttype.adapters.out.persistence.repositories.InformedConsentTypeRepositoryAdapter;

@Configuration
public class InformedConsentTypeBeansConfig {
    @Bean
    public InformedConsentTypePersistenceMapper informedConsentTypePersistenceMapper() {
        return new InformedConsentTypePersistenceMapper();
    }

    @Bean
    public InformedConsentTypeRepository informedConsentTypeRepository(InformedConsentTypeJpaRepository repository, InformedConsentTypePersistenceMapper mapper) {
        return new InformedConsentTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterInformedConsentTypeUseCase registerInformedConsentTypeUseCase(InformedConsentTypeRepository repository) {
        return new RegisterInformedConsentTypeUseCase(repository);
    }

    @Bean
    public GetInformedConsentTypeByIdUseCase getInformedConsentTypeByIdUseCase(InformedConsentTypeRepository repository) {
        return new GetInformedConsentTypeByIdUseCase(repository);
    }

    @Bean
    public ListInformedConsentTypeUseCase listInformedConsentTypeUseCase(InformedConsentTypeRepository repository) {
        return new ListInformedConsentTypeUseCase(repository);
    }

    @Bean
    public UpdateInformedConsentTypeUseCase updateInformedConsentTypeUseCase(InformedConsentTypeRepository repository) {
        return new UpdateInformedConsentTypeUseCase(repository);
    }

    @Bean
    public DeleteInformedConsentTypeUseCase deleteInformedConsentTypeUseCase(InformedConsentTypeRepository repository) {
        return new DeleteInformedConsentTypeUseCase(repository);
    }
}
