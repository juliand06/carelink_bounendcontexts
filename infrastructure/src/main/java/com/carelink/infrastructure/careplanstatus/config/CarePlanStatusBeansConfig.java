package com.carelink.infrastructure.careplanstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.careplanstatus.usecase.*;
import com.carelink.domain.careplanstatus.port.repository.CarePlanStatusRepository;
import com.carelink.infrastructure.careplanstatus.adapters.out.persistence.mappers.CarePlanStatusPersistenceMapper;
import com.carelink.infrastructure.careplanstatus.adapters.out.persistence.repositories.CarePlanStatusJpaRepository;
import com.carelink.infrastructure.careplanstatus.adapters.out.persistence.repositories.CarePlanStatusRepositoryAdapter;

@Configuration
public class CarePlanStatusBeansConfig {
    @Bean
    public CarePlanStatusPersistenceMapper carePlanStatusPersistenceMapper() {
        return new CarePlanStatusPersistenceMapper();
    }

    @Bean
    public CarePlanStatusRepository carePlanStatusRepository(CarePlanStatusJpaRepository repository, CarePlanStatusPersistenceMapper mapper) {
        return new CarePlanStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterCarePlanStatusUseCase registerCarePlanStatusUseCase(CarePlanStatusRepository repository) {
        return new RegisterCarePlanStatusUseCase(repository);
    }

    @Bean
    public GetCarePlanStatusByIdUseCase getCarePlanStatusByIdUseCase(CarePlanStatusRepository repository) {
        return new GetCarePlanStatusByIdUseCase(repository);
    }

    @Bean
    public ListCarePlanStatusUseCase listCarePlanStatusUseCase(CarePlanStatusRepository repository) {
        return new ListCarePlanStatusUseCase(repository);
    }

    @Bean
    public UpdateCarePlanStatusUseCase updateCarePlanStatusUseCase(CarePlanStatusRepository repository) {
        return new UpdateCarePlanStatusUseCase(repository);
    }

    @Bean
    public DeleteCarePlanStatusUseCase deleteCarePlanStatusUseCase(CarePlanStatusRepository repository) {
        return new DeleteCarePlanStatusUseCase(repository);
    }
}
