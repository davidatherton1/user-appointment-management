package com.perficient.managementservice.services.management;

import com.perficient.managementservice.services.appointment.AppointmentDto;
import com.perficient.managementservice.services.appointment.AppointmentServiceRestTemplate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ManagementServiceImplTests {

    @Mock
    AppointmentServiceRestTemplate appointmentService;

    @InjectMocks
    ManagementServiceImpl managementService;


    @Test
    void getApptTest(){
        UUID id = UUID.randomUUID();
        AppointmentDto appt = new AppointmentDto();
        when(appointmentService.getApptById(any())).thenReturn(appt);
        AppointmentDto foundAppt = managementService.getAppt(id);
        assertNotNull(foundAppt);
        verify(appointmentService).getApptById(any());
    }

    @Test
    void getAllApptTest(){
        List<AppointmentDto> appts = new ArrayList<>();
        appts.add(new AppointmentDto());
        appts.add(new AppointmentDto());

        when(appointmentService.getAllAppts()).thenReturn(appts);
        List<AppointmentDto> allAppts = managementService.getAllAppts();
        assertNotNull(allAppts);
        assertEquals(2, allAppts.size());
        verify(appointmentService).getAllAppts();
    }

    @Test
    void addApptTest(){
        AppointmentDto appt = new AppointmentDto();
        when(appointmentService.addAppt(any())).thenReturn(appt);
        AppointmentDto savedAppt = managementService.addAppt(appt);
        assertNotNull(savedAppt);
        verify(appointmentService).addAppt(any());
    }

    @Test
    void deleteApptTest(){
        AppointmentDto appt = new AppointmentDto();
        doNothing().when(appointmentService).deleteAppt(any());
        managementService.deleteAppt(any());
        verify(appointmentService).deleteAppt(any());
    }

    @Test
    void updateApptTest(){
        AppointmentDto appt = new AppointmentDto();
        when(appointmentService.updateAppt(any(), any())).thenReturn(appt);
        UUID id = UUID.randomUUID();
        AppointmentDto updatedAppt = managementService.updateAppt(id, appt);
        assertNotNull(updatedAppt);
        verify(appointmentService).updateAppt(id, appt);
    }

}
