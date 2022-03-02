package com.perficient.managementservice.services.management;

import com.perficient.managementservice.services.user.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto user);

    String deleteUser(UUID id);

    UserDto updateUser(UUID id, UserDto user);

    UserDto getUserById(UUID uuid);

    List<UserDto> allUsers();


}
