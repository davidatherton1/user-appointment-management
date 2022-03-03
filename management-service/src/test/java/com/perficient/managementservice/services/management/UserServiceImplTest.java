package com.perficient.managementservice.services.management;

import com.perficient.managementservice.services.user.UserDto;
import com.perficient.managementservice.services.user.UserServiceRestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {

    @Mock
    UserServiceRestTemplate userServiceRestTemplate;

    @InjectMocks
    UserServiceImpl userService;

    private UserDto userDto;
    private UUID id;

    @BeforeEach
    public void setup() {
        // Create a mock user to test
        this.userDto = new UserDto();
        this.id = UUID.randomUUID();

        this.userDto.setId(this.id);
    }

    @Test
    public void test_AllUsers_ShouldReturnEmptyList() {
        when(userServiceRestTemplate.getAllUsers())
                .thenReturn(new ArrayList<UserDto>());

        List<UserDto> resultAllUsers = userService.allUsers();


        assertNotNull(resultAllUsers);
        assertEquals(new ArrayList<UserDto>(), resultAllUsers);
    }

    @Test
    public void test_CreateUser_ShouldReturnCreatedUser() {
        when(
                userServiceRestTemplate.createUser(this.userDto))
                    .thenReturn(
                            new ResponseEntity<>(this.userDto, HttpStatus.OK)
                    );

        UserDto actualCreatedUser = userService.createUser(this.userDto);

        assertEquals(this.userDto, actualCreatedUser);
    }

    @Test
    public void test_DeleteUser_ShouldDeleteWithMessage() {
        when(userServiceRestTemplate.deleteUserById(this.id)).thenReturn(new String());

        String controllerMessage = userService.deleteUser(this.id);

        assertNotNull(controllerMessage);
    }
}