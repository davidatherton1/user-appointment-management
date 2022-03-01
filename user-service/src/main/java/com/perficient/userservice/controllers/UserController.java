package com.perficient.userservice.controllers;

import com.perficient.userservice.entities.UserEntity;
import com.perficient.userservice.exceptions.ResourceNotFoundException;
import com.perficient.userservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {
        return userService.getUsers();
    }


    // TODO: Error handling on create user. Will throw error if JSON value field "gender" is not capitalized.
    @PostMapping("/user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        UserEntity createdUser = userService.createUser(userEntity);

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUserByUUID(@RequestParam("id") UUID uuid) {
        try {
            return new ResponseEntity(userService.getUserById(uuid), HttpStatus.OK);
        } catch  (ResourceNotFoundException rnfe) {
            return new ResponseEntity(rnfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<UserEntity> updateUser(@RequestParam("id") UUID uuid, @RequestBody UserEntity userEntity) {
        try {
            UserEntity updatedUser = userService.updateUser(userEntity, uuid);

            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ResourceNotFoundException rnfe) {
            return new ResponseEntity(rnfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> removeUser(@RequestParam("id") UUID uuid) {
        try {
            userService.deleteUser(uuid);
            return new ResponseEntity<>("User with uuid: " + uuid + " has been deleted.", HttpStatus.OK);
        } catch (ResourceNotFoundException rnfe) {
            return new ResponseEntity<>(rnfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
