package com.carelink.infrastructure.biologicalsex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.biologicalsex.usecase.*;
import com.carelink.domain.biologicalsex.port.repository.BiologicalSexRepository;
import com.carelink.infrastructure.biologicalsex.adapters.out.persistence.mappers.BiologicalSexPersistenceMapper;
import com.carelink.infrastructure.biologicalsex.adapters.out.persistence.repositories.BiologicalSexJpaRepository;
import com.carelink.infrastructure.biologicalsex.adapters.out.persistence.repositories.BiologicalSexRepositoryAdapter;

@Configuration
public class BiologicalSexBeansConfig {
    @Bean
    public BiologicalSexPersistenceMapper biologicalSexPersistenceMapper() {
        return new BiologicalSexPersistenceMapper();
    }

    @Bean
    public BiologicalSexRepository biologicalSexRepository(BiologicalSexJpaRepository repository, BiologicalSexPersistenceMapper mapper) {
        return new BiologicalSexRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterBiologicalSexUseCase registerBiologicalSexUseCase(BiologicalSexRepository repository) {
        return new RegisterBiologicalSexUseCase(repository);
    }

    @Bean
    public GetBiologicalSexByIdUseCase getBiologicalSexByIdUseCase(BiologicalSexRepository repository) {
        return new GetBiologicalSexByIdUseCase(repository);
    }

    @Bean
    public ListBiologicalSexUseCase listBiologicalSexUseCase(BiologicalSexRepository repository) {
        return new ListBiologicalSexUseCase(repository);
    }

    @Bean
    public UpdateBiologicalSexUseCase updateBiologicalSexUseCase(BiologicalSexRepository repository) {
        return new UpdateBiologicalSexUseCase(repository);
    }

    @Bean
    public DeleteBiologicalSexUseCase deleteBiologicalSexUseCase(BiologicalSexRepository repository) {
        return new DeleteBiologicalSexUseCase(repository);
    }
}
