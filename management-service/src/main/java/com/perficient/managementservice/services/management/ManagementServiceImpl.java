package com.perficient.managementservice.services.management;


import com.perficient.managementservice.services.appointment.AppointmentDto;
import com.perficient.managementservice.services.appointment.AppointmentServiceRestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component
public class ManagementServiceImpl implements ManagementService{

    private final AppointmentServiceRestTemplate appointmentService;

    public ManagementServiceImpl(AppointmentServiceRestTemplate appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public void deleteAppt(UUID id) {
        appointmentService.deleteAppt(id);

    }

    @Override
    public AppointmentDto getAppt(UUID id) {
        return appointmentService.getApptById(id);
    }

    @Override
    public List<AppointmentDto> getAllAppts() {
        return appointmentService.getAllAppts();
    }

    @Override
    public AppointmentDto addAppt(AppointmentDto apptDto) {
        return appointmentService.addAppt(apptDto);
    }

    @Override
    public AppointmentDto updateAppt(UUID id, AppointmentDto apptDto) {
        return appointmentService.updateAppt(id, apptDto);
    }

    @Override
    public List<AppointmentDto> getApptbyUserId(UUID id) {
        return appointmentService.getApptByUserId(id);
    }
}
