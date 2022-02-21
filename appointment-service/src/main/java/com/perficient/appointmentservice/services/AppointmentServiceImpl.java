package com.perficient.appointmentservice.services;

import appointment.model.AppointmentDto;
import com.perficient.appointmentservice.entity.Appointment;
import com.perficient.appointmentservice.repositories.AppointmentRepository;
import com.perficient.appointmentservice.web.controllers.NotFoundException;
import com.perficient.appointmentservice.web.mappers.AppointmentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;


    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public AppointmentDto getApptById(UUID id) {
        Appointment appt = appointmentRepository.findById(id).orElseThrow(NotFoundException::new);
        return appointmentMapper.apptToApptDto(appt);
    }

    @Override
    public List<AppointmentDto> getApptsByUserId(UUID userId) {
        List<Appointment> appts = appointmentRepository.findByUser(userId);
        List<AppointmentDto> apptsDto = new ArrayList<>();
        appts.forEach(e -> apptsDto.add(appointmentMapper.apptToApptDto(e)));
        return apptsDto;
    }

    @Override
    public AppointmentDto updateAppt(UUID id, AppointmentDto apptDto) {
        Appointment appt = appointmentRepository.findById(id).orElseThrow();
        appt.setApptType(apptDto.getApptType());
        appt.setApptName(apptDto.getApptName());
        appt.setDescription(apptDto.getDescription());
        appt.setEndTime(apptDto.getEndTime());
        appt.setStartTime(apptDto.getStartTime());
        appt.setUser(apptDto.getUser());

        return appointmentMapper.apptToApptDto(appointmentRepository.save(appt));
    }


    @Override
    public AppointmentDto addAppt(AppointmentDto apptDto) {
        return appointmentMapper.apptToApptDto(appointmentRepository.save(appointmentMapper.apptDtoToAppt(apptDto)));
    }

    @Override
    public void deleteApptById(UUID id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<AppointmentDto> getAllAppts() {
        List<Appointment> appts = appointmentRepository.findAll();
        List<AppointmentDto> apptsDto = new ArrayList<>();
        appts.forEach(e -> apptsDto.add(appointmentMapper.apptToApptDto(e)));
        return apptsDto;
    }
}
