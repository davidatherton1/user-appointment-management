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

        userDto.setId(this.id);
    }


    @Test
    void createUser_ShouldReturnCreatedUser() {
        ResponseEntity<UserDto> exampleResponse = new ResponseEntity<>(HttpStatus.OK);

        when(userServiceRestTemplate.createUser(this.userDto)).thenReturn(exampleResponse);

        UserDto actualUser = userService.createUser(this.userDto);

        assertNotNull(actualUser);
    }

    @Test
    void deleteUser() {
        fail("Test yet to be implemented");
    }

    @Test
    void updateUser() {
        fail("Test yet to be implemented");
    }

    @Test
    void getUserById() {
        userService.createUser(this.userDto);

        when(userServiceRestTemplate.getUserById(this.id)).thenReturn(this.userDto);

        UserDto returnedUser = userService.getUserById(this.id);

        assertEquals(this.userDto, returnedUser);
    }

    @Test
    void allUsers() {
        when(userServiceRestTemplate.getAllUsers())
                .thenReturn(new ArrayList<>());

        List<UserDto> resultAllUsers = userService.allUsers();


        assertNotNull(resultAllUsers);
        assertEquals(new ArrayList<UserDto>(), resultAllUsers);
    }
}