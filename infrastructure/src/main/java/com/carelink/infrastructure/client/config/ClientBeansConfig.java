package com.carelink.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.client.usecase.*;
import com.carelink.domain.client.port.repository.ClientRepository;
import com.carelink.infrastructure.client.adapters.out.persistence.mappers.ClientPersistenceMapper;
import com.carelink.infrastructure.client.adapters.out.persistence.repositories.ClientJpaRepository;
import com.carelink.infrastructure.client.adapters.out.persistence.repositories.ClientRepositoryAdapter;

@Configuration
public class ClientBeansConfig {
    @Bean
    public ClientPersistenceMapper clientPersistenceMapper() {
        return new ClientPersistenceMapper();
    }

    @Bean
    public ClientRepository clientRepository(ClientJpaRepository repository, ClientPersistenceMapper mapper) {
        return new ClientRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterClientUseCase registerClientUseCase(ClientRepository repository) {
        return new RegisterClientUseCase(repository);
    }

    @Bean
    public GetClientByIdUseCase getClientByIdUseCase(ClientRepository repository) {
        return new GetClientByIdUseCase(repository);
    }

    @Bean
    public ListClientUseCase listClientUseCase(ClientRepository repository) {
        return new ListClientUseCase(repository);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(ClientRepository repository) {
        return new UpdateClientUseCase(repository);
    }

    @Bean
    public DeleteClientUseCase deleteClientUseCase(ClientRepository repository) {
        return new DeleteClientUseCase(repository);
    }
}
