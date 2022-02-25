package com.perficient.managementservice.controllers;

import com.perficient.managementservice.services.appointment.AppointmentDto;
import com.perficient.managementservice.services.management.ManagementServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ManagementController {

    private final ManagementServiceImpl managementService;

    public ManagementController(ManagementServiceImpl managementService) {
        this.managementService = managementService;
    }


    //Get all appointments
    @GetMapping ("/management/appointment")
    ResponseEntity<List<AppointmentDto>> getAllAppts(){
        List<AppointmentDto> appts = managementService.getAllAppts();

        return new ResponseEntity<>(appts, HttpStatus.OK);
    }

    //Get appointment by id
    @GetMapping("/management/appointment/{id}")
    ResponseEntity<AppointmentDto> getApptById(@PathVariable("id")UUID id){
        AppointmentDto appt = managementService.getAppt(id);

        return new ResponseEntity<>(appt, HttpStatus.OK);
    }


    //Get appointment by user id
    @GetMapping("management/appointment/user/{id}")
    ResponseEntity<List<AppointmentDto>> getApptByUserId(@PathVariable("id")UUID id ){
        List<AppointmentDto> appt = managementService.getApptbyUserId(id);

        return new ResponseEntity<>(appt, HttpStatus.OK);
    }


    //Update appointment by id
    @PutMapping("/management/appointment/{id}")
    ResponseEntity<AppointmentDto> updateAppt(@PathVariable("id") UUID id,
                                              @RequestBody @Validated AppointmentDto apptDto){
        AppointmentDto updatedAppt = managementService.updateAppt(id, apptDto);

        return new ResponseEntity<>(updatedAppt, HttpStatus.OK);

    }

    //Delete appointment by id
    @DeleteMapping("/management/appointment/{id}")
    ResponseEntity<Void> deleteAppt(@PathVariable("id") UUID id){
        managementService.deleteAppt(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Update appointment
    @PostMapping("/management/appointment")
    ResponseEntity<AppointmentDto> addAppt(@RequestBody @Validated AppointmentDto apptDto){
        AppointmentDto addedAppt = managementService.addAppt(apptDto);

        return new ResponseEntity<>(addedAppt, HttpStatus.CREATED);
    }
}