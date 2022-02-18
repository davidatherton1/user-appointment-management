package com.perficient.userservice.controllers;

import com.perficient.userservice.entities.UserEntity;
import com.perficient.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private UserService userService;

    @GetMapping("/allUsers")
    public List<UserEntity> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping("/createUser")
    public @ResponseBody void createUser(@RequestBody UserEntity userEntity) {
        userService.createUser(userEntity);
    }

    @GetMapping("/getUser")
    public UserEntity getUserByUUID(@RequestParam("id") UUID uuid) {
        return userService.getUserById(uuid);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserEntity> updateUser(@RequestParam UUID uuid, @RequestBody UserEntity userEntity) {
        UserEntity updatedUser = userService.updateUser(userEntity, uuid);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/removeUser")
    public void removeUser(@RequestParam UUID uuid) {
        userService.deleteUser(uuid);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
