package com.perficient.managementservice.services.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class UserServiceRestTemplate {

    public static final String USER_URL = "http://localhost:8080/";
    private final RestTemplate userRestTemplate;

    public UserServiceRestTemplate() {
        this.userRestTemplate = new RestTemplate();
    }

    public List<UserDto> getAllUsers() {
        StringBuilder urlBuilder = new StringBuilder(USER_URL);
        urlBuilder.append("users");

        ResponseEntity<List<UserDto>> users =  userRestTemplate.exchange(urlBuilder.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        return users.getBody();
    }

    public UserDto getUserById(UUID id) {
        StringBuilder urlBuilder = new StringBuilder(USER_URL);
        urlBuilder.append("user");
        urlBuilder.append("?id={id}");

        return userRestTemplate.getForObject(
                urlBuilder.toString(),
                UserDto.class,
                id);
    }

    public String deleteUserById(UUID id) {
        StringBuilder urlBuilder = new StringBuilder(USER_URL);
        urlBuilder.append("user");
        urlBuilder.append("?id={id}");

        ResponseEntity<String> controllerResponse =  userRestTemplate.exchange(urlBuilder.toString(),
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() {},
                id);

        return controllerResponse.getBody();
    }

    public ResponseEntity<UserDto> updateUserById(UUID id, UserDto userDto) {
        StringBuilder urlBuilder = new StringBuilder(USER_URL);
        urlBuilder.append("user");
        urlBuilder.append("?id={id}");

        HttpEntity<UserDto> userDtoHttpEntity = new HttpEntity<>(userDto);


        return userRestTemplate.exchange(urlBuilder.toString(),
                    HttpMethod.PUT,
                    userDtoHttpEntity,
                    new ParameterizedTypeReference<>() {},
                    id);
    }

    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        StringBuilder urlBuilder = new StringBuilder(USER_URL);
        urlBuilder.append("user");

        HttpEntity<UserDto> userDtoHttpEntity = new HttpEntity<>(userDto);

        return userRestTemplate.exchange(urlBuilder.toString(),
                HttpMethod.POST,
                userDtoHttpEntity,
                new ParameterizedTypeReference<>() {}
        );
    }

}
