package com.carelink.domain.appointmentmodality.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.appointmentmodality.model.aggregate.AppointmentModality;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;

public interface AppointmentModalityRepository {
    AppointmentModality save(AppointmentModality aggregate);

    Optional<AppointmentModality> findById(AppointmentModalityId id);

    List<AppointmentModality> findAll();

    void delete(AppointmentModality aggregate);
}
