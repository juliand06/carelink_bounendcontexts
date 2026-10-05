package com.carelink.infrastructure.drugadministrationroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.drugadministrationroute.usecase.*;
import com.carelink.domain.drugadministrationroute.port.repository.DrugAdministrationRouteRepository;
import com.carelink.infrastructure.drugadministrationroute.adapters.out.persistence.mappers.DrugAdministrationRoutePersistenceMapper;
import com.carelink.infrastructure.drugadministrationroute.adapters.out.persistence.repositories.DrugAdministrationRouteJpaRepository;
import com.carelink.infrastructure.drugadministrationroute.adapters.out.persistence.repositories.DrugAdministrationRouteRepositoryAdapter;

@Configuration
public class DrugAdministrationRouteBeansConfig {
    @Bean
    public DrugAdministrationRoutePersistenceMapper drugAdministrationRoutePersistenceMapper() {
        return new DrugAdministrationRoutePersistenceMapper();
    }

    @Bean
    public DrugAdministrationRouteRepository drugAdministrationRouteRepository(DrugAdministrationRouteJpaRepository repository, DrugAdministrationRoutePersistenceMapper mapper) {
        return new DrugAdministrationRouteRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterDrugAdministrationRouteUseCase registerDrugAdministrationRouteUseCase(DrugAdministrationRouteRepository repository) {
        return new RegisterDrugAdministrationRouteUseCase(repository);
    }

    @Bean
    public GetDrugAdministrationRouteByIdUseCase getDrugAdministrationRouteByIdUseCase(DrugAdministrationRouteRepository repository) {
        return new GetDrugAdministrationRouteByIdUseCase(repository);
    }

    @Bean
    public ListDrugAdministrationRouteUseCase listDrugAdministrationRouteUseCase(DrugAdministrationRouteRepository repository) {
        return new ListDrugAdministrationRouteUseCase(repository);
    }

    @Bean
    public UpdateDrugAdministrationRouteUseCase updateDrugAdministrationRouteUseCase(DrugAdministrationRouteRepository repository) {
        return new UpdateDrugAdministrationRouteUseCase(repository);
    }

    @Bean
    public DeleteDrugAdministrationRouteUseCase deleteDrugAdministrationRouteUseCase(DrugAdministrationRouteRepository repository) {
        return new DeleteDrugAdministrationRouteUseCase(repository);
    }
}
