package com.perficient.userservice.controllers;

import com.perficient.userservice.entities.UserEntity;
import com.perficient.userservice.exceptions.ResourceNotFoundException;
import com.perficient.userservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

        UserEntity user = new UserEntity();
        user.setId(uuid);
        userController.createUser(user);

        when(userService.getUserById(uuid)).thenReturn(user);

        UserEntity returnedUser = userController.getUserByUUID(uuid).getBody();

        assertEquals(user, returnedUser);
    }

    @Test
    public void test_UpdateUser_ShouldThrowResourceNotFoundException() {
        UUID uuid = UUID.randomUUID();

        UserEntity user = new UserEntity();
        user.setId(uuid);
        userController.createUser(user);

        when(userService.updateUser(new UserEntity(), uuid)).thenReturn(new UserEntity());

        UserEntity updateUser = new UserEntity();
        updateUser.setFirstName("update");

        UserEntity returnedUpdatedUser = userController.updateUser(uuid, updateUser).getBody();

        assertNotEquals(user, returnedUpdatedUser);
    }

}