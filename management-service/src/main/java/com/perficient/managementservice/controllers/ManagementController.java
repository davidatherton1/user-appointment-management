package com.perficient.managementservice.controllers;

import com.perficient.managementservice.services.appointment.AppointmentDto;
import com.perficient.managementservice.services.management.ManagementServiceImpl;
import com.perficient.managementservice.services.management.UserServiceImpl;
import com.perficient.managementservice.services.user.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@RestController
public class ManagementController {

    private final ManagementServiceImpl managementService;
    private final UserServiceImpl userService;

    public ManagementController(ManagementServiceImpl managementService, UserServiceImpl userService) {
        this.managementService = managementService;
        this.userService = userService;
    }

    @GetMapping ("/management/appointment")
    ResponseEntity<List<AppointmentDto>> getAllAppts(){
        List<AppointmentDto> appts = managementService.getAllAppts();

        return new ResponseEntity<>(appts, HttpStatus.OK);
    }

    @GetMapping("/management/appointment/{id}")
    ResponseEntity<AppointmentDto> getApptById(@PathVariable("id")UUID id){
        AppointmentDto appt = managementService.getAppt(id);

        return new ResponseEntity<>(appt, HttpStatus.OK);
    }

    @GetMapping("management/appointment/user/{id}")
    ResponseEntity<List<AppointmentDto>> getApptByUserId(@PathVariable("id")UUID id ){
        List<AppointmentDto> appt = managementService.getApptbyUserId(id);

        return new ResponseEntity<>(appt, HttpStatus.OK);
    }

    @PutMapping("/management/appointment/{id}")
    ResponseEntity<AppointmentDto> updateAppt(@PathVariable("id") UUID id,
                                              @RequestBody @Validated AppointmentDto apptDto){
        AppointmentDto updatedAppt = managementService.updateAppt(id, apptDto);

        return new ResponseEntity<>(updatedAppt, HttpStatus.OK);

    }

    @DeleteMapping("/management/appointment/{id}")
    ResponseEntity<Void> deleteAppt(@PathVariable("id") UUID id){
        managementService.deleteAppt(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/management/appointment")
    ResponseEntity<AppointmentDto> addAppt(@RequestBody @Validated AppointmentDto apptDto){
        AppointmentDto addedAppt = managementService.addAppt(apptDto);

        return new ResponseEntity<>(addedAppt, HttpStatus.CREATED);
    }

    @GetMapping("/management/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.allUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/management/user")
    public ResponseEntity<UserDto> getUserById(@RequestParam("id") UUID id) {
        UserDto userDto  = userService.getUserById(id);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/management/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/management/user")
    public ResponseEntity<UserDto> updateUser(@RequestParam("id") UUID id, @RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
        } catch (HttpClientErrorException hcee) {
            return new ResponseEntity(hcee.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/management/user")
    public ResponseEntity<String> deleteUser(@RequestParam("id") UUID id) {
        try {
            String serverResponse = userService.deleteUser(id);

            return new ResponseEntity<>(serverResponse, HttpStatus.OK);
        } catch (HttpClientErrorException hcee) {
            return new ResponseEntity<>(hcee.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
