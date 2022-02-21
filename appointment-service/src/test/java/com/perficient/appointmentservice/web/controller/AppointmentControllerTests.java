package com.perficient.appointmentservice.web.controller;

import appointment.model.AppointmentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.appointmentservice.services.AppointmentServiceImpl;
import com.perficient.appointmentservice.web.controllers.AppointmentController;
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

@WebMvcTest(controllers = AppointmentController.class)
public class AppointmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AppointmentServiceImpl appointmentService;

    @Test
    void getApptById() throws Exception {
        when(appointmentService.getApptById(any())).thenReturn(validApptDto());

        mockMvc.perform(get("/appointment/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteApptById() throws Exception {
        doNothing().when(appointmentService).deleteApptById(any());

        mockMvc.perform(delete("/appointment/delete/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAppts() throws Exception{
        List<AppointmentDto> appts = new ArrayList<>();
        appts.add(validApptDto());
        appts.add(validApptDto());
        when(appointmentService.getAllAppts()).thenReturn(appts);

        mockMvc.perform(get("/appointment/allAppts"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllApptsByUserId() throws Exception{
        List<AppointmentDto> appts = new ArrayList<>();
        appts.add(validApptDto());
        appts.add(validApptDto());
        UUID id = UUID.randomUUID();
        when(appointmentService.getApptsByUserId(any())).thenReturn(appts);

        mockMvc.perform(get("/appointment/allAppts/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void updateAppt() throws Exception {
        when(appointmentService.updateAppt(any(), any())).thenReturn(validApptDto());

        String apptDtoJson = objectMapper.writeValueAsString(validApptDto());

        mockMvc.perform((put("/appointment/update/" + UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(apptDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void addAppt() throws Exception {
        when(appointmentService.addAppt(any())).thenReturn(validApptDto());

        String apptDtoJson = objectMapper.writeValueAsString(validApptDto());

        mockMvc.perform(post("/appointment/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(apptDtoJson))
                .andExpect(status().isCreated());
    }

    AppointmentDto validApptDto(){
        return AppointmentDto.builder()
                .apptName("Test Appointment")
                .apptType("Test Type")
                .build();
    }
}
