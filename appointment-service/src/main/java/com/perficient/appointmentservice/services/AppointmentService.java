package com.perficient.appointmentservice.services;

import appointment.model.AppointmentDto;
import com.perficient.appointmentservice.entity.Appointment;

import java.util.List;
import java.util.UUID;


public interface AppointmentService {

    AppointmentDto getApptById(UUID id);

    AppointmentDto updateAppt(UUID id, AppointmentDto apptDto);

    AppointmentDto addAppt(AppointmentDto appt);

    void deleteApptById(UUID id);

    List<AppointmentDto> getAllAppts();
}
