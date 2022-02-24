package com.perficient.managementservice.services.appointment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class AppointmentServiceRestTemplate {

    public static final String APPOINTMENT_PATH = "http://localhost:8080/appointment/";
    private final RestTemplate restTemplate;

    public AppointmentServiceRestTemplate() {

        this.restTemplate = new RestTemplate();
    }

    public List<AppointmentDto> getAllAppts(){
        ResponseEntity<List<AppointmentDto>> appts = restTemplate.exchange(APPOINTMENT_PATH, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AppointmentDto>>(){});

        return appts.getBody();
    }

    public AppointmentDto getApptById(UUID id){
        ResponseEntity<AppointmentDto> appt = restTemplate.exchange(APPOINTMENT_PATH + "{id}", HttpMethod.GET, null,
                new ParameterizedTypeReference<AppointmentDto>(){}, id);

        return appt.getBody();

    }

    public void deleteAppt(UUID id){
        ResponseEntity<Void> appt = restTemplate.exchange(APPOINTMENT_PATH + "{id}", HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Void>() {}, id);
    }

    public AppointmentDto updateAppt(UUID id, AppointmentDto appt){

        HttpEntity<AppointmentDto> requestUpdate = new HttpEntity<>(appt);

        ResponseEntity<AppointmentDto> updatedAppt = restTemplate.exchange(APPOINTMENT_PATH + "{id}", HttpMethod.PUT, requestUpdate,
                new ParameterizedTypeReference<AppointmentDto>(){}, id);

        return updatedAppt.getBody();
    }

    public AppointmentDto addAppt(AppointmentDto apptDto){

        HttpEntity<AppointmentDto> requestUpdate = new HttpEntity<>(apptDto);

        ResponseEntity<AppointmentDto> addedAppt = restTemplate.exchange(APPOINTMENT_PATH, HttpMethod.POST, requestUpdate,
                new ParameterizedTypeReference<AppointmentDto>(){});

        return addedAppt.getBody();
    }
}
