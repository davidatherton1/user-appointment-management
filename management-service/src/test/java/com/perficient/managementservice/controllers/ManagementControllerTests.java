package com.perficient.managementservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.managementservice.services.appointment.AppointmentDto;
import com.perficient.managementservice.services.management.ManagementServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ManagementController.class)
public class ManagementControllerTests {

    private static final String APPOINTMENT_URL = "/management/appointment/";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ManagementServiceImpl managementService;

    @Test
    void getAppt() throws Exception{
        when(managementService.getAppt(any())).thenReturn(validApptDto());

        mockMvc.perform(get(APPOINTMENT_URL + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAppts() throws Exception{
        List<AppointmentDto> appts = new ArrayList<>();
        appts.add(validApptDto());
        appts.add(validApptDto());
        when(managementService.getAllAppts()).thenReturn(appts);

        mockMvc.perform(get(APPOINTMENT_URL))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAppt() throws Exception{
        doNothing().when(managementService).deleteAppt(any());

        mockMvc.perform(delete(APPOINTMENT_URL + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void updateAppt() throws Exception{
        when(managementService.updateAppt(any(), any())).thenReturn(validApptDto());

        String apptDtoJson = objectMapper.writeValueAsString(validApptDto());

        mockMvc.perform((put(APPOINTMENT_URL + UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(apptDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void addAppt() throws Exception{
        when(managementService.addAppt(any())).thenReturn(validApptDto());

        String apptDtoJson = objectMapper.writeValueAsString(validApptDto());

        mockMvc.perform((post(APPOINTMENT_URL))
                .contentType(MediaType.APPLICATION_JSON)
                .content(apptDtoJson))
                .andExpect(status().isCreated());


    }

    @Test
    void getApptByUserId() throws Exception{
        List<AppointmentDto> appts = new ArrayList<>();
        appts.add(validApptDto());
        appts.add(validApptDto());
        when(managementService.getApptbyUserId(any())).thenReturn(appts);

        mockMvc.perform(get(APPOINTMENT_URL + "user/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    AppointmentDto validApptDto(){
        return AppointmentDto.builder()
                .apptName("Test Appointment")
                .apptType("Test Type")
                .build();
    }
}
