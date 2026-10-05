package com.carelink.infrastructure.trustedcontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.trustedcontact.usecase.*;
import com.carelink.domain.trustedcontact.port.repository.TrustedContactRepository;
import com.carelink.infrastructure.trustedcontact.adapters.out.persistence.mappers.TrustedContactPersistenceMapper;
import com.carelink.infrastructure.trustedcontact.adapters.out.persistence.repositories.TrustedContactJpaRepository;
import com.carelink.infrastructure.trustedcontact.adapters.out.persistence.repositories.TrustedContactRepositoryAdapter;

@Configuration
public class TrustedContactBeansConfig {
    @Bean
    public TrustedContactPersistenceMapper trustedContactPersistenceMapper() {
        return new TrustedContactPersistenceMapper();
    }

    @Bean
    public TrustedContactRepository trustedContactRepository(TrustedContactJpaRepository repository, TrustedContactPersistenceMapper mapper) {
        return new TrustedContactRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterTrustedContactUseCase registerTrustedContactUseCase(TrustedContactRepository repository) {
        return new RegisterTrustedContactUseCase(repository);
    }

    @Bean
    public GetTrustedContactByIdUseCase getTrustedContactByIdUseCase(TrustedContactRepository repository) {
        return new GetTrustedContactByIdUseCase(repository);
    }

    @Bean
    public ListTrustedContactUseCase listTrustedContactUseCase(TrustedContactRepository repository) {
        return new ListTrustedContactUseCase(repository);
    }

    @Bean
    public UpdateTrustedContactUseCase updateTrustedContactUseCase(TrustedContactRepository repository) {
        return new UpdateTrustedContactUseCase(repository);
    }

    @Bean
    public DeleteTrustedContactUseCase deleteTrustedContactUseCase(TrustedContactRepository repository) {
        return new DeleteTrustedContactUseCase(repository);
    }
}
