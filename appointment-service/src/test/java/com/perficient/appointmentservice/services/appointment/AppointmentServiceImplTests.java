package com.perficient.appointmentservice.services.appointment;

import appointment.model.AppointmentDto;
import com.perficient.appointmentservice.entity.Appointment;
import com.perficient.appointmentservice.repositories.AppointmentRepository;
import com.perficient.appointmentservice.services.AppointmentServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import com.perficient.appointmentservice.web.mappers.AppointmentMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest
public class AppointmentServiceImplTests {

    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    AppointmentMapper appointmentMapper;

    @InjectMocks
    AppointmentServiceImpl service;

    @Test
    void getApptByIdTest(){
        UUID id = UUID.randomUUID();
        Appointment appointment = new Appointment();
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));
        AppointmentDto foundAppt = service.getApptById(id);
//        assertNotNull(foundAppt);
        verify(appointmentRepository).findById(id);
    }

    @Test
    void addApptTest(){
        Appointment appointment = new Appointment();
        AppointmentDto appointmentDto = new AppointmentDto();
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        when(appointmentMapper.apptDtoToAppt(appointmentDto)).thenReturn(appointment);
        when(appointmentMapper.apptToApptDto(appointment)).thenReturn(appointmentDto);
        AppointmentDto savedAppointment = service.addAppt(appointmentDto);
        assertNotNull(savedAppointment);
        verify(appointmentRepository).save(appointment);
    }

    @Test
    void findAllApptsTest(){
        List<Appointment> appts = new ArrayList<>();
        appts.add(new Appointment());
        appts.add(new Appointment());
        when(appointmentRepository.findAll()).thenReturn(appts);
        List<AppointmentDto> allAppts = service.getAllAppts();
        assertNotNull(allAppts);
        assertEquals(2, allAppts.size());
        verify(appointmentRepository).findAll();

    }

    @Test
    void findAllApptsByUserIdTest(){
        List<Appointment> appts = new ArrayList<>();
        appts.add(new Appointment());
        appts.add(new Appointment());
        when(appointmentRepository.findByUser(any())).thenReturn(appts);
        UUID uuid = UUID.randomUUID();
        List<AppointmentDto> allAppts = service.getApptsByUserId(uuid);
        assertNotNull(allAppts);
        assertEquals(2, allAppts.size());
        verify(appointmentRepository).findByUser(uuid);
    }

    @Test
    void deleteApptByIdTest(){
        UUID id = UUID.randomUUID();
        doNothing().when(appointmentRepository).deleteById(id);
        service.deleteApptById(id);
        verify(appointmentRepository).deleteById(id);

    }

    @Test
    void updateApptTest(){
        AppointmentDto apptDto = AppointmentDto.builder()
                .apptName("Test Appointment")
                .apptType("")
                .description("")
                .endTime(OffsetDateTime.now())
                .startTime(OffsetDateTime.now())
                .user(UUID.randomUUID())
                .build();
        Appointment appt = new Appointment();
        UUID id = UUID.randomUUID();
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appt));
        when(appointmentRepository.save(appt)).thenReturn(appt);
        when(appointmentMapper.apptToApptDto(appt)).thenReturn(apptDto);
        AppointmentDto updatedDto = service.updateAppt(id, apptDto);
        assertNotNull(updatedDto);
        verify(appointmentRepository).findById(id);
        verify(appointmentRepository).save(appt);
        verify(appointmentMapper).apptToApptDto(appt);

    }

}
