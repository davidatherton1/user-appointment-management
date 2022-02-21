package com.perficient.appointmentservice.web.controllers;


import appointment.model.AppointmentDto;
import com.perficient.appointmentservice.entity.Appointment;
import com.perficient.appointmentservice.services.AppointmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("appointment/{apptId}")
    public ResponseEntity<AppointmentDto> getApptById(@PathVariable("apptId")UUID apptId){
        log.debug(String.valueOf(apptId));
        return new ResponseEntity<>(appointmentService.getApptById(apptId), HttpStatus.OK);
    }

    @GetMapping("appointment/allAppts")
    public ResponseEntity<List<AppointmentDto>> getAllAppts(){
        return new ResponseEntity<>(appointmentService.getAllAppts(), HttpStatus.OK);
    }

    @GetMapping("/appointment/allAppts/{userId}")
    public ResponseEntity<List<AppointmentDto>> getAllApptsByUserId(@PathVariable("userId") UUID userId){
        return new ResponseEntity<>(appointmentService.getApptsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(path = "appointment")
    public ResponseEntity<AppointmentDto> addAppt(@RequestBody @Validated AppointmentDto apptDto){
        return new ResponseEntity<>(appointmentService.addAppt(apptDto), HttpStatus.CREATED);
    }

    @DeleteMapping("appointment/delete/{apptId}")
    public ResponseEntity<Void> deleteAppt(@PathVariable("apptId") UUID apptId){
        appointmentService.deleteApptById(apptId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("appointment/update/{apptId}")
    public ResponseEntity<AppointmentDto> updateAppt(@PathVariable("apptId") UUID apptId,
                                       @RequestBody @Validated AppointmentDto apptDto){
        return new ResponseEntity<>(appointmentService.updateAppt(apptId, apptDto), HttpStatus.OK);
    }
}
