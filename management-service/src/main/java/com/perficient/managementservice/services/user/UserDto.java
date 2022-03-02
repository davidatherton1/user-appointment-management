package com.perficient.managementservice.services.user;


import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Setter
public class UserDto {

    private UUID id;

    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String emailAddress;
    private String phoneNumber;

    private UUID appointment;
}
