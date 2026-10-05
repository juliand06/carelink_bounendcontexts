package com.carelink.domain.appointment.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.appointment.model.aggregate.Appointment;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;

public interface AppointmentRepository {
    Appointment save(Appointment aggregate);

    Optional<Appointment> findById(AppointmentId id);

    List<Appointment> findAll();

    void delete(Appointment aggregate);
}
