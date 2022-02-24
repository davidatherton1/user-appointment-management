package com.perficient.userservice;

import com.perficient.userservice.entities.UserEntity;
import com.perficient.userservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

    @Mock
    UserService userService;

    @Test
    public void userCreationTest() {
        UserEntity userEntity = new UserEntity();

//        doNothing().when(userService).createUser(userEntity);
        when(userService.createUser(userEntity)).thenReturn(userEntity);

        userService.createUser(userEntity);
    }

    @Test
    public void getUserListTest() {
        UserEntity user = new UserEntity();

        List<UserEntity> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);

        userService.createUser(user);

        when(userService.getUsers()).thenReturn(expectedUsers);
        List<UserEntity> actualUsers = userService.getUsers();


        assertEquals(actualUsers, expectedUsers);
    }


    @Test
    public void userServiceTest_GetUser_EnsureReturnsUserById() {
        // Generate a random UUID to use through the test
        UUID randomUuid = UUID.randomUUID();

        // Create a user entity to insert to the service
        UserEntity userEntity = new UserEntity();
        userEntity.setId(randomUuid);

        userService.createUser(userEntity);

        when(userService.getUserById(randomUuid)).thenReturn(userEntity);

        // Retrieve the user we just added
        UserEntity returnedUser = userService.getUserById(randomUuid);

        assertEquals(userEntity, returnedUser);
    }

}
