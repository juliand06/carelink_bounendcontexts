package com.carelink.domain.appointmenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.appointmenttype.model.aggregate.AppointmentType;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;

public interface AppointmentTypeRepository {
    AppointmentType save(AppointmentType aggregate);

    Optional<AppointmentType> findById(AppointmentTypeId id);

    List<AppointmentType> findAll();

    void delete(AppointmentType aggregate);
}
