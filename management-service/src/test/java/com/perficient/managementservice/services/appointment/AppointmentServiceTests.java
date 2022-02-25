package com.perficient.managementservice.services.appointment;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AppointmentServiceTests {

    public static final String APPOINTMENT_PATH = "http://localhost:8080/appointment/";

    @Mock
    RestTemplate restTemplate;

    @Mock
    RestTemplateBuilder restTemplateBuilder;

    @InjectMocks
    AppointmentServiceRestTemplate appointmentService;

//    @Test
//    void getAllAppts(){
//        ResponseEntity<List<AppointmentDto>> response = new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
//        when(restTemplateBuilder.build()).thenReturn(new RestTemplate());
//        when(restTemplate.exchange(Mockito.anyString() ,Mockito.<HttpMethod> any(), Mockito.<HttpEntity<?>> any(), Mockito.<Class<List<AppointmentDto>>> any())).thenReturn(response);
//
//        List<AppointmentDto> appts = appointmentService.getAllAppts();
//        assertNotNull(appts);
//        verify(restTemplate).exchange(Mockito.anyString() ,Mockito.<HttpMethod> any(), Mockito.<HttpEntity<?>> any(), Mockito.<Class<List<AppointmentDto>>> any());
//    }


}
