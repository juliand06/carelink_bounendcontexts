package com.carelink.infrastructure.appointment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carelink.application.appointment.usecase.*;
import com.carelink.domain.appointment.port.repository.AppointmentRepository;
import com.carelink.infrastructure.appointment.adapters.out.persistence.mappers.AppointmentPersistenceMapper;
import com.carelink.infrastructure.appointment.adapters.out.persistence.repositories.AppointmentJpaRepository;
import com.carelink.infrastructure.appointment.adapters.out.persistence.repositories.AppointmentRepositoryAdapter;

@Configuration
public class AppointmentBeansConfig {
    @Bean
    public AppointmentPersistenceMapper appointmentPersistenceMapper() {
        return new AppointmentPersistenceMapper();
    }

    @Bean
    public AppointmentRepository appointmentRepository(AppointmentJpaRepository repository, AppointmentPersistenceMapper mapper) {
        return new AppointmentRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterAppointmentUseCase registerAppointmentUseCase(AppointmentRepository repository) {
        return new RegisterAppointmentUseCase(repository);
    }

    @Bean
    public GetAppointmentByIdUseCase getAppointmentByIdUseCase(AppointmentRepository repository) {
        return new GetAppointmentByIdUseCase(repository);
    }

    @Bean
    public ListAppointmentUseCase listAppointmentUseCase(AppointmentRepository repository) {
        return new ListAppointmentUseCase(repository);
    }

    @Bean
    public UpdateAppointmentUseCase updateAppointmentUseCase(AppointmentRepository repository) {
        return new UpdateAppointmentUseCase(repository);
    }

    @Bean
    public DeleteAppointmentUseCase deleteAppointmentUseCase(AppointmentRepository repository) {
        return new DeleteAppointmentUseCase(repository);
    }
}
