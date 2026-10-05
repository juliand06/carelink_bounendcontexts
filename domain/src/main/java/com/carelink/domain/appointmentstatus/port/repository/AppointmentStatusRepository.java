package com.carelink.domain.appointmentstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.appointmentstatus.model.aggregate.AppointmentStatus;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;

public interface AppointmentStatusRepository {
    AppointmentStatus save(AppointmentStatus aggregate);

    Optional<AppointmentStatus> findById(AppointmentStatusId id);

    List<AppointmentStatus> findAll();

    void delete(AppointmentStatus aggregate);
}
