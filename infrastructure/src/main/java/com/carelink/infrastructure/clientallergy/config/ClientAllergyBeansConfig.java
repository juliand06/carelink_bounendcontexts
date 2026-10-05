package com.carelink.infrastructure.clientallergy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.clientallergy.usecase.*;
import com.carelink.domain.clientallergy.port.repository.ClientAllergyRepository;
import com.carelink.infrastructure.clientallergy.adapters.out.persistence.mappers.ClientAllergyPersistenceMapper;
import com.carelink.infrastructure.clientallergy.adapters.out.persistence.repositories.ClientAllergyJpaRepository;
import com.carelink.infrastructure.clientallergy.adapters.out.persistence.repositories.ClientAllergyRepositoryAdapter;

@Configuration
public class ClientAllergyBeansConfig {
    @Bean
    public ClientAllergyPersistenceMapper clientAllergyPersistenceMapper() {
        return new ClientAllergyPersistenceMapper();
    }

    @Bean
    public ClientAllergyRepository clientAllergyRepository(ClientAllergyJpaRepository repository, ClientAllergyPersistenceMapper mapper) {
        return new ClientAllergyRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterClientAllergyUseCase registerClientAllergyUseCase(ClientAllergyRepository repository) {
        return new RegisterClientAllergyUseCase(repository);
    }

    @Bean
    public GetClientAllergyByIdUseCase getClientAllergyByIdUseCase(ClientAllergyRepository repository) {
        return new GetClientAllergyByIdUseCase(repository);
    }

    @Bean
    public ListClientAllergyUseCase listClientAllergyUseCase(ClientAllergyRepository repository) {
        return new ListClientAllergyUseCase(repository);
    }

    @Bean
    public UpdateClientAllergyUseCase updateClientAllergyUseCase(ClientAllergyRepository repository) {
        return new UpdateClientAllergyUseCase(repository);
    }

    @Bean
    public DeleteClientAllergyUseCase deleteClientAllergyUseCase(ClientAllergyRepository repository) {
        return new DeleteClientAllergyUseCase(repository);
    }
}
