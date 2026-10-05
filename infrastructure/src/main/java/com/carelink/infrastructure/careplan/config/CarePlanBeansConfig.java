package com.carelink.infrastructure.careplan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.careplan.usecase.*;
import com.carelink.domain.careplan.port.repository.CarePlanRepository;
import com.carelink.infrastructure.careplan.adapters.out.persistence.mappers.CarePlanPersistenceMapper;
import com.carelink.infrastructure.careplan.adapters.out.persistence.repositories.CarePlanJpaRepository;
import com.carelink.infrastructure.careplan.adapters.out.persistence.repositories.CarePlanRepositoryAdapter;

@Configuration
public class CarePlanBeansConfig {
    @Bean
    public CarePlanPersistenceMapper carePlanPersistenceMapper() {
        return new CarePlanPersistenceMapper();
    }

    @Bean
    public CarePlanRepository carePlanRepository(CarePlanJpaRepository repository, CarePlanPersistenceMapper mapper) {
        return new CarePlanRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterCarePlanUseCase registerCarePlanUseCase(CarePlanRepository repository) {
        return new RegisterCarePlanUseCase(repository);
    }

    @Bean
    public GetCarePlanByIdUseCase getCarePlanByIdUseCase(CarePlanRepository repository) {
        return new GetCarePlanByIdUseCase(repository);
    }

    @Bean
    public ListCarePlanUseCase listCarePlanUseCase(CarePlanRepository repository) {
        return new ListCarePlanUseCase(repository);
    }

    @Bean
    public UpdateCarePlanUseCase updateCarePlanUseCase(CarePlanRepository repository) {
        return new UpdateCarePlanUseCase(repository);
    }

    @Bean
    public DeleteCarePlanUseCase deleteCarePlanUseCase(CarePlanRepository repository) {
        return new DeleteCarePlanUseCase(repository);
    }
}
