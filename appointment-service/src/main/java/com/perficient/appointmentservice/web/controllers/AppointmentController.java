package com.perficient.appointmentservice.web.controllers;


import appointment.model.AppointmentDto;
import com.perficient.appointmentservice.entity.Appointment;
import com.perficient.appointmentservice.services.AppointmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    //Get appointment by id
    @GetMapping("appointment/{apptId}")
    public ResponseEntity<AppointmentDto> getApptById(@PathVariable("apptId")UUID apptId){
//        return new ResponseEntity<>(appointmentService.getApptById(apptId), HttpStatus.OK);

        try{
            return new ResponseEntity<>(appointmentService.getApptById(apptId), HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment with id doesn't exist");
        }
//        } catch (IllegalArgumentException ex){
//            System.out.println("Throwing illegal argument exception");
//          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not valid");
//        }
    }

    //Get all appointments
    @GetMapping("appointment")
    public ResponseEntity<List<AppointmentDto>> getAllAppts(){
        return new ResponseEntity<>(appointmentService.getAllAppts(), HttpStatus.OK);
    }

    //Get appointment by the user id
    @GetMapping("/appointment/user/{userId}")
    public ResponseEntity<List<AppointmentDto>> getAllApptsByUserId(@PathVariable("userId") UUID userId){
        return new ResponseEntity<>(appointmentService.getApptsByUserId(userId), HttpStatus.OK);
    }

    //Add an appointment
    @PostMapping(path = "appointment")
    public ResponseEntity<AppointmentDto> addAppt(@RequestBody @Validated AppointmentDto apptDto){
        return new ResponseEntity<>(appointmentService.addAppt(apptDto), HttpStatus.CREATED);
    }

    //Delete an appointment by id
    @DeleteMapping("appointment/{apptId}")
    public ResponseEntity<Void> deleteAppt(@PathVariable("apptId") UUID apptId){
        appointmentService.deleteApptById(apptId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Update an appointment by id
    @PutMapping("appointment/{apptId}")
    public ResponseEntity<AppointmentDto> updateAppt(@PathVariable("apptId") UUID apptId,
                                       @RequestBody @Validated AppointmentDto apptDto){
        try {
            return new ResponseEntity<>(appointmentService.updateAppt(apptId, apptDto), HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment with id doesn't exist");
        }
    }
}
