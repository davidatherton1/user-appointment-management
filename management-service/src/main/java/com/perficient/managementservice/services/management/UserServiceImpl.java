package com.perficient.managementservice.services.management;

import com.perficient.managementservice.services.user.UserDto;
import com.perficient.managementservice.services.user.UserServiceRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component
public class UserServiceImpl implements UserService{

    private UserServiceRestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(UserServiceRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDto createUser(UserDto user) {
        return restTemplate.createUser(user).getBody();
    }

    @Override
    public String deleteUser(UUID id) {
        return restTemplate.deleteUserById(id);
    }

    @Override
    public UserDto updateUser(UUID id, UserDto user) {
        return restTemplate.updateUserById(id, user).getBody();
    }

    @Override
    public UserDto getUserById(UUID uuid) {
        return restTemplate.getUserById(uuid);
    }

    @Override
    public List<UserDto> allUsers() {
        return restTemplate.getAllUsers();
    }
}
