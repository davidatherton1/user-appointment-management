package com.perficient.userservice.controllers;

import com.perficient.userservice.entities.UserEntity;
import com.perficient.userservice.exceptions.ResourceNotFoundException;
import com.perficient.userservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @Test
    public void test_GetAllUsers_ShouldReturnEmptyList() {
        when(userService.getUsers()).thenReturn(new ArrayList<>());

        List<UserEntity> users = userController.getAllUsers();

        assertNotNull(users);
        assertTrue(users.isEmpty());
    }

    @Test
    public void test_CreateUser_ShouldReturnCreatedUser() {
        when(userService.createUser(new UserEntity())).thenReturn(new UserEntity());

        UserEntity createdUser = userController.createUser(new UserEntity()).getBody();

        assertNotNull(createdUser);
    }

    @Test
    public void test_GetUserByUUID_ShouldReturnAUser() {
        UUID uuid = UUID.randomUUID();

        UserEntity actualUser = new UserEntity();
        actualUser.setId(uuid);
        userController.createUser(actualUser);

        when(userService.getUserById(uuid)).thenReturn(actualUser);

        UserEntity returnedUser = userController.getUserByUUID(uuid).getBody();

        assertEquals(actualUser, returnedUser);
    }

    @Test
    public void test_UpdateUser_ShouldThrowResourceNotFoundException() {
        UUID randomUUID = UUID.randomUUID();

        when(
                userService.updateUser(new UserEntity(), randomUUID))
                    .thenThrow(
                            new ResourceNotFoundException("User", "UUID", randomUUID)
                    );


        HttpStatus expectedStatusCode = HttpStatus.NOT_FOUND;
        ResponseEntity<UserEntity> returnMessage = userController.updateUser(randomUUID, new UserEntity());

        assertEquals(expectedStatusCode, returnMessage.getStatusCode());
    }

    @Test
    public void test_DeleteUser_ShouldThrowResourceNotFoundException() {
        UUID randomUUID = UUID.randomUUID();

        doThrow(new ResourceNotFoundException("User", "UUID", randomUUID)).when(userService).deleteUser(randomUUID);

        userController.removeUser(randomUUID);
    }

    @Test
    public void test_DeleteUser_ShouldGiveOKStatusCode() {
        // Insert user first to be deleted
        UUID randomUUID = UUID.randomUUID();

        UserEntity actualUser = new UserEntity();
        actualUser.setId(randomUUID);

        userController.createUser(actualUser);

        doNothing().when(userService).deleteUser(randomUUID);

        ResponseEntity<String> controllerResponse = userController.removeUser(randomUUID);
        HttpStatus expectedStatusCode = HttpStatus.OK;

        assertEquals(expectedStatusCode, controllerResponse.getStatusCode());
    }

}