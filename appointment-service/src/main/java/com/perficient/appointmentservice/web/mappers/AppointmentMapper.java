package com.perficient.appointmentservice.web.mappers;

import appointment.model.AppointmentDto;
import com.perficient.appointmentservice.entity.Appointment;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentDto apptToApptDto(Appointment appt);

    Appointment apptDtoToAppt(AppointmentDto apptDto);
}
