package com.carelink.infrastructure.clienttrustedcontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.clienttrustedcontact.usecase.*;
import com.carelink.domain.clienttrustedcontact.port.repository.ClientTrustedContactRepository;
import com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.mappers.ClientTrustedContactPersistenceMapper;
import com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.repositories.ClientTrustedContactJpaRepository;
import com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.repositories.ClientTrustedContactRepositoryAdapter;

@Configuration
public class ClientTrustedContactBeansConfig {
    @Bean
    public ClientTrustedContactPersistenceMapper clientTrustedContactPersistenceMapper() {
        return new ClientTrustedContactPersistenceMapper();
    }

    @Bean
    public ClientTrustedContactRepository clientTrustedContactRepository(ClientTrustedContactJpaRepository repository, ClientTrustedContactPersistenceMapper mapper) {
        return new ClientTrustedContactRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterClientTrustedContactUseCase registerClientTrustedContactUseCase(ClientTrustedContactRepository repository) {
        return new RegisterClientTrustedContactUseCase(repository);
    }

    @Bean
    public GetClientTrustedContactByIdUseCase getClientTrustedContactByIdUseCase(ClientTrustedContactRepository repository) {
        return new GetClientTrustedContactByIdUseCase(repository);
    }

    @Bean
    public ListClientTrustedContactUseCase listClientTrustedContactUseCase(ClientTrustedContactRepository repository) {
        return new ListClientTrustedContactUseCase(repository);
    }

    @Bean
    public UpdateClientTrustedContactUseCase updateClientTrustedContactUseCase(ClientTrustedContactRepository repository) {
        return new UpdateClientTrustedContactUseCase(repository);
    }

    @Bean
    public DeleteClientTrustedContactUseCase deleteClientTrustedContactUseCase(ClientTrustedContactRepository repository) {
        return new DeleteClientTrustedContactUseCase(repository);
    }
}
