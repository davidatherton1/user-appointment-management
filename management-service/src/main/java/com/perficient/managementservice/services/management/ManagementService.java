package com.perficient.managementservice.services.management;


import com.perficient.managementservice.services.appointment.AppointmentDto;

import java.util.List;
import java.util.UUID;

public interface ManagementService {

    void deleteAppt(UUID id);

    AppointmentDto getAppt(UUID id);

    List<AppointmentDto> getAllAppts();

    AppointmentDto addAppt(AppointmentDto apptDto);

    AppointmentDto updateAppt(UUID id, AppointmentDto apptDto);

    List<AppointmentDto> getApptbyUserId(UUID id);
}
