package com.carelink.infrastructure.appointmentmodality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.appointmentmodality.usecase.*;
import com.carelink.domain.appointmentmodality.port.repository.AppointmentModalityRepository;
import com.carelink.infrastructure.appointmentmodality.adapters.out.persistence.mappers.AppointmentModalityPersistenceMapper;
import com.carelink.infrastructure.appointmentmodality.adapters.out.persistence.repositories.AppointmentModalityJpaRepository;
import com.carelink.infrastructure.appointmentmodality.adapters.out.persistence.repositories.AppointmentModalityRepositoryAdapter;

@Configuration
public class AppointmentModalityBeansConfig {
    @Bean
    public AppointmentModalityPersistenceMapper appointmentModalityPersistenceMapper() {
        return new AppointmentModalityPersistenceMapper();
    }

    @Bean
    public AppointmentModalityRepository appointmentModalityRepository(AppointmentModalityJpaRepository repository, AppointmentModalityPersistenceMapper mapper) {
        return new AppointmentModalityRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterAppointmentModalityUseCase registerAppointmentModalityUseCase(AppointmentModalityRepository repository) {
        return new RegisterAppointmentModalityUseCase(repository);
    }

    @Bean
    public GetAppointmentModalityByIdUseCase getAppointmentModalityByIdUseCase(AppointmentModalityRepository repository) {
        return new GetAppointmentModalityByIdUseCase(repository);
    }

    @Bean
    public ListAppointmentModalityUseCase listAppointmentModalityUseCase(AppointmentModalityRepository repository) {
        return new ListAppointmentModalityUseCase(repository);
    }

    @Bean
    public UpdateAppointmentModalityUseCase updateAppointmentModalityUseCase(AppointmentModalityRepository repository) {
        return new UpdateAppointmentModalityUseCase(repository);
    }

    @Bean
    public DeleteAppointmentModalityUseCase deleteAppointmentModalityUseCase(AppointmentModalityRepository repository) {
        return new DeleteAppointmentModalityUseCase(repository);
    }
}
